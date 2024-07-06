package com.gfg.userservice.service.serviceImpl;

import com.gfg.userservice.domain.entity.Credential;
import com.gfg.userservice.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = credentialRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new User(
                credential.getUsername(),
                credential.getPassword(),
                credential.getIsEnabled(),
                credential.getIsAccountNonExpired(),
                credential.getIsCredentialsNonExpired(),
                credential.getIsAccountNonLocked(),
                Collections.singletonList(new SimpleGrantedAuthority(credential.getRoleBasedAuthority().name()))
        );
    }
}
