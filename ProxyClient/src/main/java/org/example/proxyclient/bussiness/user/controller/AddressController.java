package org.example.proxyclient.bussiness.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.user.model.AddressDTO;
import org.example.proxyclient.bussiness.user.model.response.AddressUserServiceCollectionDtoResponse;
import org.example.proxyclient.bussiness.user.service.AddressClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressClientService addressClientService;

    @GetMapping
    public ResponseEntity<AddressUserServiceCollectionDtoResponse> findAll() {
        return ResponseEntity.ok(this.addressClientService.findAll().getBody());
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDTO> findById(@PathVariable("addressId") final String addressId) {
        return ResponseEntity.ok(this.addressClientService.findById(addressId).getBody());
    }

    @PostMapping
    public ResponseEntity<AddressDTO> save(@RequestBody final AddressDTO addressDto) {
        return ResponseEntity.ok(this.addressClientService.save(addressDto).getBody());
    }

    @PutMapping
    public ResponseEntity<AddressDTO> update(@RequestBody final AddressDTO addressDto) {
        return ResponseEntity.ok(this.addressClientService.update(addressDto).getBody());
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDTO> update(@PathVariable("addressId") final String addressId, @RequestBody final AddressDTO addressDto) {
        return ResponseEntity.ok(this.addressClientService.update(addressDto).getBody());
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("addressId") final String addressId) {
        return ResponseEntity.ok(this.addressClientService.deleteById(addressId).getBody());
    }



}

