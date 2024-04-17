package com.gfg.userservice.helperClass;


import com.gfg.userservice.domain.Credential;
import com.gfg.userservice.domain.User;
import com.gfg.userservice.dto.CredentialDTO;
import com.gfg.userservice.dto.UserDTO;

public interface UserMapping {

    static UserDTO map(final User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .imageUrl(user.getImageUrl())
                .email(user.getEmail())
                .phone(user.getPhone())
                .credentialDTO(
                        CredentialDTO.builder()
                                .credentialId(user.getCredential().getCredentialId())
                                .username(user.getCredential().getUsername())
                                .password(user.getCredential().getPassword())
                                .roleBasedAuthority(user.getCredential().getRoleBasedAuthority())
                                .isEnabled(user.getCredential().getIsEnabled())
                                .isAccountNonExpired(user.getCredential().getIsAccountNonExpired())
                                .isAccountNonLocked(user.getCredential().getIsAccountNonLocked())
                                .isCredentialNonExpired(user.getCredential().getIsCredentialsNonExpired())
                                .build())
                .build();
    }

    public static User map(final UserDTO userDto) {
        // Map UserDTO to User entity
        User user = User.builder()
                .userId(userDto.getUserId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .imageUrl(userDto.getImageUrl())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .build();

        // Map CredentialDTO to Credential entity
        CredentialDTO credentialDTO = userDto.getCredentialDTO();
        Credential credential = Credential.builder()
                .credentialId(credentialDTO.getCredentialId())
                .username(credentialDTO.getUsername())
                .password(credentialDTO.getPassword())
                .roleBasedAuthority(credentialDTO.getRoleBasedAuthority())
                .isEnabled(credentialDTO.getIsEnabled())
                .isAccountNonExpired(credentialDTO.getIsAccountNonExpired())
                .isAccountNonLocked(credentialDTO.getIsAccountNonLocked())
                .isCredentialsNonExpired(credentialDTO.getIsCredentialNonExpired())
                .user(user) // Associate the User entity with the Credential
                .build();

        // Set the Credential entity to the User
        user.setCredential(credential);

        return user;
    }



}
