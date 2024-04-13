package org.example.proxyclient.bussiness.auth.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.proxyclient.bussiness.auth.model.request.AuthenticationRequest;
import org.example.proxyclient.bussiness.auth.model.response.AuthenticationResponse;
import org.example.proxyclient.bussiness.auth.service.AuthenticationService;
import org.example.proxyclient.exception.IllegalAuthenticationCredentialsException;
import org.example.proxyclient.jwt.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse authenticate(final AuthenticationRequest authenticationRequest) {

        log.info("** AuthenticationResponse, authenticate user service*\n");

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new IllegalAuthenticationCredentialsException("#### Bad credentials! ####");
        }

        return new AuthenticationResponse(this.jwtService.generateToken(this.userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername())));
    }

    @Override
    public Boolean authenticate(final String jwt) {
        return null;
    }
}
