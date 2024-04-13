package org.example.proxyclient.security;

import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.user.model.RoleBasedAuthority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
   // private final JwtRequestFilter jwtRequestFilter;


    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
    }


    protected void configure(final HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/", "index", "**/css/**", "**/js/**").permitAll()
                .requestMatchers("/api/authenticate/**").permitAll()
                .requestMatchers("/api/categories/**").permitAll()
                .requestMatchers("/api/products/**").permitAll()
                .requestMatchers("/api/**")
                .hasAnyRole(RoleBasedAuthority.ROLE_USER.getRole(),
                        RoleBasedAuthority.ROLE_ADMIN.getRole())
                .requestMatchers("/actuator/health/**", "/actuator/info/**")
                .permitAll()
                .requestMatchers("/actuator/**")
                .hasAnyRole(RoleBasedAuthority.ROLE_ADMIN.getRole())
                .anyRequest().authenticated()
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
               // .addFilterBefore(this.jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

  //  @Bean

//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}
