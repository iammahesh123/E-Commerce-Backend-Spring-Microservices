package org.example.proxyclient.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.proxyclient.jwt.service.JwtService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;





    @Component
    @Slf4j
    @RequiredArgsConstructor
    public class JwtRequestFilter extends OncePerRequestFilter {

        private final UserDetailsService userDetailsService;
        private final JwtService jwtService;

        @Override
        protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
                throws ServletException, IOException {

            log.info("**JwtRequestFilter, once per request, validating and extracting token*\n");

            final var authorizationHeader = request.getHeader("Authorization");

            String username = null;
            String jwt = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                username = jwtService.extractUsername(jwt);
            }

            try {
                // Add URL patterns that should be excluded from JWT authentication
                String requestURI = request.getRequestURI();
                String method = request.getMethod();
                if ((requestURI.equals("/api/users") || requestURI.startsWith("/api/credentials")) && method.equals("POST")) {
                    // Skip JWT authentication for POST requests to /api/users and /api/credentials
                    filterChain.doFilter(request, response);
                    return;
                }

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                    if (this.jwtService.validateToken(jwt, userDetails)) {
                        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }

                filterChain.doFilter(request, response);
                log.info("**Jwt request filtered!*\n");
            } catch (AuthenticationCredentialsNotFoundException | BadCredentialsException |
                     InsufficientAuthenticationException e) {
                log.error("Authentication error: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }



