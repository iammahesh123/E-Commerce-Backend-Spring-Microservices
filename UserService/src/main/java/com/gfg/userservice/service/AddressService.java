package com.gfg.userservice.service;

import com.gfg.userservice.domain.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> findAll();
    AddressDTO findById(final Integer addressId);
    AddressDTO save(final AddressDTO addressDto);
    AddressDTO update(final AddressDTO addressDto);
    AddressDTO update(final Integer addressId, final AddressDTO addressDto);
    void deleteById(final Integer addressId);
}
