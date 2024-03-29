package com.gfg.userservice.serviceImplementation;

import com.gfg.userservice.dto.CredentialDTO;
import com.gfg.userservice.exceptions.CredentialNotFoundException;
import com.gfg.userservice.helperClass.CredentialMapping;
import com.gfg.userservice.repository.CredentialRepository;
import com.gfg.userservice.service.CredentialService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CredentialServiceImplementation implements CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;



    @Override
    public List<CredentialDTO> findAll() {
        log.info("CredentialsDTO, find all the credentials");
        return this.credentialRepository.findAll()
                .stream()
                .map(CredentialMapping::map)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public CredentialDTO findById(Integer credentialId) {
        log.info("Credentials, Find the Credentials by Id");
        return this.credentialRepository
                .findById(credentialId)
                .map(CredentialMapping::map)
                .orElseThrow(() -> new CredentialNotFoundException(String.format("Credentials not found of this %d",credentialId)));
    }

    @Override
    public CredentialDTO save(CredentialDTO credentialDto) {
        log.info("CredentialDTO, Save the Credentials");

        return CredentialMapping.map(this.credentialRepository.save(CredentialMapping.map(credentialDto)));
    }

    @Override
    public CredentialDTO update(CredentialDTO credentialDto) {
        log.info("CredentialDTO, Update the Credentials");
        return CredentialMapping.map(this.credentialRepository.save(CredentialMapping.map(credentialDto)));
    }

    @Override
    public CredentialDTO update(Integer credentialId, CredentialDTO credentialDto) {
        log.info("CredentialDTO, Update the Credentials by using Id");
        return CredentialMapping.map(this.credentialRepository.save(CredentialMapping.map(this.findById(credentialId))));
    }

    @Override
    public void deleteById(Integer credentialId) {
        log.info("CredentialDTO, Delete the Credentials");
        this.credentialRepository.deleteById(credentialId);

    }

    @Override
    public CredentialDTO findByUsername(final String username) {
        log.info("CredentialDTO, Find the Credentials by using UserId");
        return null;
        //return CredentialMapping.map(this.credentialRepository.findByUsername(username));
                //.orElseThrow(() -> new UserObjectNotFoundException(String.format("#### Credential with username: %s not found! ####", username)));
    }
}
