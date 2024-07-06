package com.gfg.userservice.service;

import com.gfg.userservice.domain.dto.VerificationDTO;

import java.util.List;

public interface VerificationTokenService {

    List<VerificationDTO> findAll();
    VerificationDTO findById(final Integer verificationTokenId);
    VerificationDTO save(final VerificationDTO verificationTokenDto);
    VerificationDTO update(final VerificationDTO verificationTokenDto);
    VerificationDTO update(final Integer verificationTokenId, final VerificationDTO verificationTokenDto);
    void deleteById(final Integer verificationTokenId);

}
