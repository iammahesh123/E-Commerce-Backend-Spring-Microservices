package org.example.proxyclient.bussiness.user.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.proxyclient.bussiness.user.model.AddressDTO;
import org.example.proxyclient.bussiness.user.model.response.AddressUserServiceCollectionDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "USER-SERVICE", contextId = "addressClientService", path = "/user-service/api/address")
public interface AddressClientService {

    @GetMapping
    ResponseEntity<AddressUserServiceCollectionDtoResponse> findAll();

    @GetMapping("/{addressId}")
    ResponseEntity<AddressDTO> findById(
            @PathVariable("addressId")
            @NotBlank(message = "*Input must not blank!**")
            @Valid final String addressId);

    @PostMapping
    ResponseEntity<AddressDTO> save(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final AddressDTO addressDto);

    @PutMapping
    ResponseEntity<AddressDTO> update(
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final AddressDTO addressDto);

    @PutMapping("/{addressId}")
    ResponseEntity<AddressDTO> update(
            @PathVariable("addressId")
            @NotBlank(message = "*Input must not blank!**") final String addressId,
            @RequestBody
            @NotNull(message = "*Input must not NULL!**")
            @Valid final AddressDTO addressDto);

    @DeleteMapping("/{addressId}")
    ResponseEntity<Boolean> deleteById(@PathVariable("addressId") @NotBlank(message = "*Input must not blank!**") @Valid final String addressId);

}
