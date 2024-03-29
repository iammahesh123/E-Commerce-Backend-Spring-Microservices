package com.gfg.userservice.service;

import com.gfg.userservice.dto.CredentialDTO;

import java.util.List;

public interface CredentialService {

    List<CredentialDTO> findAll();
    CredentialDTO findById(final Integer credentialId);
    CredentialDTO save(final CredentialDTO credentialDto);
    CredentialDTO update(final CredentialDTO credentialDto);
    CredentialDTO update(final Integer credentialId, final CredentialDTO credentialDto);
    void deleteById(final Integer credentialId);
    CredentialDTO findByUsername(final String username);
}
