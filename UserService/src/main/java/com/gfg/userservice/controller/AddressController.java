package com.gfg.userservice.controller;

import com.gfg.userservice.domain.dto.AddressDTO;
import com.gfg.userservice.response.ResponseCollectionDTO;
import com.gfg.userservice.service.AddressService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/address"})
@Slf4j
@RequiredArgsConstructor
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<ResponseCollectionDTO<AddressDTO>> findAll() {
        log.info("AddressController, fetch all the address");
        return ResponseEntity.ok(new ResponseCollectionDTO<>(this.addressService.findAll()));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDTO> findById(@PathVariable("addressId") @NotBlank(message = "Input is not blank")
                                               @Valid final String addressId) {
        log.info("AddressDTO, fetch the address by using Address Id");
        return ResponseEntity.ok(this.addressService.findById(Integer.parseInt(addressId.strip())));

    }

    @PostMapping
    public ResponseEntity<AddressDTO> save(
            @RequestBody
            @NotNull(message = "Input must not NULL")
            @Valid final AddressDTO addressDto
    ) {
        log.info("AddressController, Save the address");
        return ResponseEntity.ok(this.addressService.save(addressDto));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDTO> update(
            @RequestBody
            @NotNull(message = "Input must not NULL")
            @Valid final AddressDTO addressDto
    ) {
        log.info("AddressDTo, Update the address");
        return ResponseEntity.ok(this.addressService.update(addressDto));
    }

    @DeleteMapping("/addressId")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("addressId")
            @NotBlank(message = "Input must not blank")
            @Valid final String addressId
    ) {
        log.info("AddressDTO, Delete the Address");
        this.addressService.deleteById(Integer.parseInt(addressId));
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
