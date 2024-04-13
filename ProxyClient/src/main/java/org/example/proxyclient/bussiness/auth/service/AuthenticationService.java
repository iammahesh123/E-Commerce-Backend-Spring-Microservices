package org.example.proxyclient.bussiness.auth.service;

import org.example.proxyclient.bussiness.auth.model.request.AuthenticationRequest;
import org.example.proxyclient.bussiness.auth.model.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse authenticate(final AuthenticationRequest authenticationRequest);
    Boolean authenticate(final String jwt);

}
