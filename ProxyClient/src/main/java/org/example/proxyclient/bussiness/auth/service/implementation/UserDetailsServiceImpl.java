package org.example.proxyclient.bussiness.auth.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.proxyclient.bussiness.user.model.CredentialDTO;
import org.example.proxyclient.bussiness.user.model.UserDetailsImpl;
import org.example.proxyclient.constant.AppConstant;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String API_URL = AppConstant.DiscoveredDomainsApi.USER_SERVICE_HOST + "/api/credential";
    private final RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.info("**UserDetails, load user by username*\n");
        return new UserDetailsImpl(this.restTemplate.getForObject(API_URL + "/username/" + username, CredentialDTO.class));
    }
}
