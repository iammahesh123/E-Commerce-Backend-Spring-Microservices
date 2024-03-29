package com.gfg.userservice.helperClass;

import com.gfg.userservice.domain.Credential;
import com.gfg.userservice.domain.User;
import com.gfg.userservice.dto.CredentialDTO;
import com.gfg.userservice.dto.UserDTO;

public interface UserMapping {

    public static UserDTO map(final User user) {

        return UserDTO.builder().
                userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .imageUrl(user.getImageUrl())
                .email(user.getEmail())
                .phone(user.getPhone())
                .credentialDTO(CredentialDTO.builder()
                        .credentialId(user.getCredential().getCredentialId())
                        .username(user.getCredential().getUsername())
                        .password(user.getCredential().getPassword())
                        .roleBasesAuthority(user.getCredential().getRoleBasedAuthority())
                        .isEnabled(user.getCredential().getIsEnabled())
                        .isAccountNonExpired(user.getCredential().getIsAccountNonExpired())
                        .isAccountNonLocked(user.getCredential().getIsEnabled())
                        .isCredentialNonExpired(user.getCredential().getIsCredentialsNonExpired())
                        .build()
                ).build();

    }

    public static User map(final UserDTO userDTO) {
        return User.builder()
                .userId(userDTO.getUserId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .imageUrl(userDTO.getImageUrl())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .credential(
                        Credential.builder()
                                .credentialId(userDTO.getCredentialDTO().getCredentialId())
                                .username(userDTO.getCredentialDTO().getUsername())
                                .password(userDTO.getCredentialDTO().getPassword())
                                .roleBasedAuthority(userDTO.getCredentialDTO().getRoleBasesAuthority())
                                .isEnabled(userDTO.getCredentialDTO().getIsEnabled())
                                .isAccountNonExpired(userDTO.getCredentialDTO().getIsAccountNonExpired())
                                .isAccountNonLocked(userDTO.getCredentialDTO().getIsAccountNonLocked())
                                .isCredentialsNonExpired(userDTO.getCredentialDTO().getIsCredentialNonExpired())
                                .build())
                .build();
    }
}
