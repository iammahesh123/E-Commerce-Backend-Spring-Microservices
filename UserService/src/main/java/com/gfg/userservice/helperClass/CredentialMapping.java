package com.gfg.userservice.helperClass;

import com.gfg.userservice.domain.Credential;
import com.gfg.userservice.domain.User;
import com.gfg.userservice.dto.CredentialDTO;
import com.gfg.userservice.dto.UserDTO;

public interface CredentialMapping {

    public static CredentialDTO map(final Credential credential) {
        return CredentialDTO.builder()
                .credentialId(credential.getCredentialId())
                .username(credential.getUsername())
                .password(credential.getPassword())
                .roleBasesAuthority(credential.getRoleBasedAuthority())
                .isEnabled(credential.getIsEnabled())
                .isAccountNonExpired(credential.getIsAccountNonExpired())
                .isAccountNonLocked(credential.getIsAccountNonLocked())
                .isCredentialNonExpired(credential.getIsCredentialsNonExpired())
                .userDTO(
                        UserDTO.builder()
                                .userId(credential.getUser().getUserId())
                                .firstName(credential.getUser().getFirstName())
                                .lastName(credential.getUser().getLastName())
                                .imageUrl(credential.getUser().getImageUrl())
                                .email(credential.getUser().getEmail())
                                .phone(credential.getUser().getPhone())
                                .build())
                .build();
    }

    public static Credential map(final CredentialDTO credentialDto) {
        return Credential.builder()
                .credentialId(credentialDto.getCredentialId())
                .username(credentialDto.getUsername())
                .password(credentialDto.getPassword())
                .roleBasedAuthority(credentialDto.getRoleBasesAuthority())
                .isEnabled(credentialDto.getIsEnabled())
                .isAccountNonExpired(credentialDto.getIsAccountNonExpired())
                .isAccountNonLocked(credentialDto.getIsAccountNonLocked())
                .isCredentialsNonExpired(credentialDto.getIsCredentialNonExpired())
                .user(
                        User.builder()
                                .userId(credentialDto.getUserDTO().getUserId())
                                .firstName(credentialDto.getUserDTO().getFirstName())
                                .lastName(credentialDto.getUserDTO().getLastName())
                                .imageUrl(credentialDto.getUserDTO().getImageUrl())
                                .email(credentialDto.getUserDTO().getEmail())
                                .phone(credentialDto.getUserDTO().getPhone())
                                .build())
                .build();
    }
}
