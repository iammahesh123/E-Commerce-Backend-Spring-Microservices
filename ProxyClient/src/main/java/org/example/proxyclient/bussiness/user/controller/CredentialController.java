package org.example.proxyclient.bussiness.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.user.model.CredentialDTO;
import org.example.proxyclient.bussiness.user.model.response.CredentialUserServiceCollectionDtoResponse;
import org.example.proxyclient.bussiness.user.service.CredentialClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credentials")
@RequiredArgsConstructor
public class CredentialController {

    private final CredentialClientService credentialClientService;

    @GetMapping
    public ResponseEntity<CredentialUserServiceCollectionDtoResponse> findAll() {
        return ResponseEntity.ok(this.credentialClientService.findAll().getBody());
    }

    @GetMapping("/{credentialId}")
    public ResponseEntity<CredentialDTO> findById(@PathVariable("credentialId") final String credentialId) {
        return ResponseEntity.ok(this.credentialClientService.findById(credentialId).getBody());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<CredentialDTO> findByCredentialname(@PathVariable("username") final String username) {
        return ResponseEntity.ok(this.credentialClientService.findByUsername(username).getBody());
    }

    @PostMapping
    public ResponseEntity<CredentialDTO> save(@RequestBody final CredentialDTO credentialDto) {
        return ResponseEntity.ok(this.credentialClientService.save(credentialDto).getBody());
    }

    @PutMapping
    public ResponseEntity<CredentialDTO> update(@RequestBody final CredentialDTO credentialDto) {
        return ResponseEntity.ok(this.credentialClientService.update(credentialDto).getBody());
    }

    @PutMapping("/{credentialId}")
    public ResponseEntity<CredentialDTO> update(@PathVariable("credentialId") final String credentialId, @RequestBody final CredentialDTO credentialDto) {
        return ResponseEntity.ok(this.credentialClientService.update(credentialDto).getBody());
    }

    @DeleteMapping("/{credentialId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("credentialId") final String credentialId) {
        return ResponseEntity.ok(this.credentialClientService.deleteById(credentialId).getBody());
    }

}





