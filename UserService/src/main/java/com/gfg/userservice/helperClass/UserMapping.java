package com.gfg.userservice.helperClass;

import com.gfg.userservice.domain.Credential;
import com.gfg.userservice.domain.User;
import com.gfg.userservice.dto.CredentialDTO;
import com.gfg.userservice.dto.UserDTO;

public interface UserMapping {

    public static UserDTO map(final User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .imageUrl(user.getImageUrl())
                .email(user.getEmail())
                .phone(user.getPhone())
                .credentialDTO(CredentialDTO.builder()
                        .credentialId(user.getCredential().getCredentialId())
                        .username(user.getCredential().getUsername())
                        .roleBasedAuthority(user.getCredential().getRoleBasedAuthority())
                        .isEnabled(user.getCredential().getIsEnabled())
                        .isAccountNonExpired(user.getCredential().getIsAccountNonExpired())
                        .isAccountNonLocked(user.getCredential().getIsAccountNonLocked())
                        .isCredentialNonExpired(user.getCredential().getIsCredentialsNonExpired())
                        .build())
                .build();
    }

    public static User map(final UserDTO userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .imageUrl(userDto.getImageUrl())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .credential(
                        Credential.builder()
                                .credentialId(userDto.getCredentialDTO().getCredentialId())
                                .username(userDto.getCredentialDTO().getUsername())
                                .password(userDto.getCredentialDTO().getPassword())
                                .roleBasedAuthority(userDto.getCredentialDTO().getRoleBasedAuthority())
                                .isEnabled(userDto.getCredentialDTO().getIsEnabled())
                                .isAccountNonExpired(userDto.getCredentialDTO().getIsAccountNonExpired())
                                .isAccountNonLocked(userDto.getCredentialDTO().getIsAccountNonLocked())
                                .isCredentialsNonExpired(userDto.getCredentialDTO().getIsCredentialNonExpired())
                                .build())
                .build();
    }

}
