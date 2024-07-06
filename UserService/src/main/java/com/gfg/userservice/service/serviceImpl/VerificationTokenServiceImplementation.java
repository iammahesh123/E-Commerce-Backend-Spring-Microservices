package com.gfg.userservice.service.serviceImpl;

import com.gfg.userservice.domain.dto.VerificationDTO;
import com.gfg.userservice.exceptions.VerificationTokenNotFoundException;
import com.gfg.userservice.helperClass.VerificationTokenMapping;
import com.gfg.userservice.repository.VerificationTokenRepository;
import com.gfg.userservice.service.VerificationTokenService;
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
public class  VerificationTokenServiceImplementation implements VerificationTokenService {

    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenServiceImplementation(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public List<VerificationDTO> findAll() {
        log.info("VerificationDTo, Fetch all the verification Tokens");
        return this.verificationTokenRepository
                .findAll().stream().map(VerificationTokenMapping::map)
                .distinct().collect(Collectors.toList());
    }

    @Override
    public VerificationDTO findById(Integer verificationTokenId) {
        log.info("VerificationDTo, Fetch all the verification Tokens using VerificationId");
        return this.verificationTokenRepository.findById(verificationTokenId)
                .map(VerificationTokenMapping::map)
                .orElseThrow(() -> new VerificationTokenNotFoundException(String.format("Verification Token with id: %d not found",verificationTokenId)));
    }

    @Override
    public VerificationDTO save(VerificationDTO verificationTokenDto) {
        log.info("VerificationDTo, Save the verification Tokens");
        return VerificationTokenMapping.map(this.verificationTokenRepository.save(VerificationTokenMapping.map(verificationTokenDto)));
    }

    @Override
    public VerificationDTO update(VerificationDTO verificationTokenDto) {
        log.info("VerificationDTo, Update the verification Tokens");
        return VerificationTokenMapping.map(this.verificationTokenRepository.save(VerificationTokenMapping.map(verificationTokenDto)));
    }

    @Override
    public VerificationDTO update(Integer verificationTokenId, VerificationDTO verificationTokenDto) {
        log.info("VerificationDTo, Update the verification Tokens by Using VerificationTokenId");
        return VerificationTokenMapping.map(this.verificationTokenRepository.save(VerificationTokenMapping.map(this.findById(verificationTokenId))));
    }

    @Override
    public void deleteById(Integer verificationTokenId) {
        log.info("VerificationDTo, Update the verification Tokens");
        this.verificationTokenRepository.deleteById(verificationTokenId);

    }
}
