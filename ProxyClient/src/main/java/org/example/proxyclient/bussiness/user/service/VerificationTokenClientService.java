package org.example.proxyclient.bussiness.user.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.proxyclient.bussiness.user.model.VerificationTokenDTO;
import org.example.proxyclient.bussiness.user.model.response.VerificationUserTokenServiceCollectionDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "USER-SERVICE", contextId = "verificationTokenClientService", path = "/user-service/api/verificationTokens")
public interface VerificationTokenClientService {

    @GetMapping
    ResponseEntity<VerificationUserTokenServiceCollectionDtoResponse> findAll();

    @GetMapping("/{verificationTokenId}")
    ResponseEntity<VerificationTokenDTO> findById(
            @PathVariable("verificationTokenId")
            @NotBlank(message = "*Input must not blank!**")
            @Valid final String verificationTokenId);

    @PostMapping
    ResponseEntity<VerificationTokenDTO> save(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final VerificationTokenDTO verificationTokenDto);

    @PutMapping
    ResponseEntity<VerificationTokenDTO> update(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final VerificationTokenDTO verificationTokenDto);

    @PutMapping("/{verificationTokenId}")
    ResponseEntity<VerificationTokenDTO> update(
            @PathVariable("verificationTokenId")
            @NotBlank(message = "*Input must not blank!**") final String verificationTokenId,
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final VerificationTokenDTO verificationTokenDto);

    @DeleteMapping("/{verificationTokenId}")
    ResponseEntity<Boolean> deleteById(@PathVariable("verificationTokenId") @NotBlank(message = "*Input must not blank!**") @Valid final String verificationTokenId);

}
