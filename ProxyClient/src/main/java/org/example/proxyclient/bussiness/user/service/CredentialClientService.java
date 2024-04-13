package org.example.proxyclient.bussiness.user.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.proxyclient.bussiness.user.model.CredentialDTO;
import org.example.proxyclient.bussiness.user.model.response.CredentialUserServiceCollectionDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "USER-SERVICE", contextId = "credentialClientService", path = "/user-service/api/credentials" )
public interface CredentialClientService {

    @GetMapping
    ResponseEntity<CredentialUserServiceCollectionDtoResponse> findAll();

    @GetMapping("/{credentialId}")
    ResponseEntity<CredentialDTO> findById(
            @PathVariable("credentialId")
            @NotBlank(message = "*Input must not blank!**")
            @Valid final String credentialId);

    @GetMapping("/username/{username}")
    ResponseEntity<CredentialDTO> findByUsername(
            @PathVariable("username")
            @NotBlank(message = "*Input must not blank!**")
            @Valid final String username);

    @PostMapping
    ResponseEntity<CredentialDTO> save(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final CredentialDTO credentialDto);

    @PutMapping
    ResponseEntity<CredentialDTO> update(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final CredentialDTO credentialDto);

    @PutMapping("/{credentialId}")
    ResponseEntity<CredentialDTO> update(
            @PathVariable("credentialId")
            @NotBlank(message = "*Input must not blank!**") final String credentialId,
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final CredentialDTO credentialDto);

    @DeleteMapping("/{credentialId}")
    ResponseEntity<Boolean> deleteById(@PathVariable("credentialId") @NotBlank(message = "*Input must not blank!**") @Valid final String credentialId);

}
