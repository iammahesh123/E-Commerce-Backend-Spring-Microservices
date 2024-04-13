package org.example.proxyclient.bussiness.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.user.model.VerificationTokenDTO;
import org.example.proxyclient.bussiness.user.model.response.VerificationUserTokenServiceCollectionDtoResponse;
import org.example.proxyclient.bussiness.user.service.VerificationTokenClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verificationTokens")
@RequiredArgsConstructor
public class VerificationTokenController {

    private final VerificationTokenClientService verificationTokenClientService;

    @GetMapping
    public ResponseEntity<VerificationUserTokenServiceCollectionDtoResponse> findAll() {
        return ResponseEntity.ok(this.verificationTokenClientService.findAll().getBody());
    }

    @GetMapping("/{verificationTokenId}")
    public ResponseEntity<VerificationTokenDTO> findById(@PathVariable("verificationTokenId") final String verificationTokenId) {
        return ResponseEntity.ok(this.verificationTokenClientService.findById(verificationTokenId).getBody());
    }

    @PostMapping
    public ResponseEntity<VerificationTokenDTO> save(@RequestBody final VerificationTokenDTO verificationTokenDto) {
        return ResponseEntity.ok(this.verificationTokenClientService.save(verificationTokenDto).getBody());
    }

    @PutMapping
    public ResponseEntity<VerificationTokenDTO> update(@RequestBody final VerificationTokenDTO verificationTokenDto) {
        return ResponseEntity.ok(this.verificationTokenClientService.update(verificationTokenDto).getBody());
    }

    @PutMapping("/{verificationTokenId}")
    public ResponseEntity<VerificationTokenDTO> update(@PathVariable("verificationTokenId") final String verificationTokenId, @RequestBody final VerificationTokenDTO verificationTokenDto) {
        return ResponseEntity.ok(this.verificationTokenClientService.update(verificationTokenDto).getBody());
    }

    @DeleteMapping("/{verificationTokenId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("verificationTokenId") final String verificationTokenId) {
        return ResponseEntity.ok(this.verificationTokenClientService.deleteById(verificationTokenId).getBody());
    }



}
