package com.gfg.userservice.helperClass;

import com.gfg.userservice.domain.Credential;
import com.gfg.userservice.domain.VerificationToken;
import com.gfg.userservice.dto.CredentialDTO;
import com.gfg.userservice.dto.VerificationDTO;

public interface VerificationTokenMapping {
    public static VerificationDTO map(final VerificationToken verificationToken) {
        return VerificationDTO.builder()
                .verificationTokenId(verificationToken.getVerificationTokenId())
                .token(verificationToken.getToken())
                .expireDate(verificationToken.getExpireDate())
                .credentialDTO(
                        CredentialDTO.builder()
                                .credentialId(verificationToken.getCredential().getCredentialId())
                                .username(verificationToken.getCredential().getUsername())
                                .password(verificationToken.getCredential().getPassword())
                                .roleBasesAuthority(verificationToken.getCredential().getRoleBasedAuthority())
                                .isEnabled(verificationToken.getCredential().getIsEnabled())
                                .isAccountNonExpired(verificationToken.getCredential().getIsAccountNonExpired())
                                .isAccountNonLocked(verificationToken.getCredential().getIsAccountNonLocked())
                                .isCredentialNonExpired(verificationToken.getCredential().getIsCredentialsNonExpired())
                                .build())
                .build();
    }

    public static VerificationToken map(final VerificationDTO verificationTokenDto) {
        return VerificationToken.builder()
                .verificationTokenId(verificationTokenDto.getVerificationTokenId())
                .token(verificationTokenDto.getToken())
                .expireDate(verificationTokenDto.getExpireDate())
                .credential(
                        Credential.builder()
                                .credentialId(verificationTokenDto.getCredentialDTO().getCredentialId())
                                .username(verificationTokenDto.getCredentialDTO().getUsername())
                                .password(verificationTokenDto.getCredentialDTO().getPassword())
                                .roleBasedAuthority(verificationTokenDto.getCredentialDTO().getRoleBasesAuthority())
                                .isEnabled(verificationTokenDto.getCredentialDTO().getIsEnabled())
                                .isAccountNonExpired(verificationTokenDto.getCredentialDTO().getIsAccountNonExpired())
                                .isAccountNonLocked(verificationTokenDto.getCredentialDTO().getIsAccountNonLocked())
                                .isCredentialsNonExpired(verificationTokenDto.getCredentialDTO().getIsCredentialNonExpired())
                                .build())
                .build();
    }

}