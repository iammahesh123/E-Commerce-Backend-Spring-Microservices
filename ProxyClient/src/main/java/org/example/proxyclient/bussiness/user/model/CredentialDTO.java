package org.example.proxyclient.bussiness.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CredentialDTO {
    private Integer credentialId;
    private String username;
    private String password;
    private RoleBasedAuthority roleBasedAuthority;
    private Boolean isEnabled;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;

    @JsonProperty("user")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private UserDTO userDto;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Set<VerificationTokenDTO> verificationTokenDtos;
}
