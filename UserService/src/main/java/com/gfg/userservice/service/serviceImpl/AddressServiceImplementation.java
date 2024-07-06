package com.gfg.userservice.service.serviceImpl;

import com.gfg.userservice.domain.dto.AddressDTO;
import com.gfg.userservice.exceptions.AddressNotFoundException;
import com.gfg.userservice.helperClass.AddressMapping;
import com.gfg.userservice.repository.AddressRepository;
import com.gfg.userservice.service.AddressService;
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
public class AddressServiceImplementation implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public List<AddressDTO> findAll() {
        log.info("Address DTO, fetch the all address");
        return this.addressRepository.findAll()
                .stream()
                .map(AddressMapping::map)
                .distinct().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public AddressDTO findById(Integer addressId) {
        log.info("AdressDTO, fetech the address by using Id");
        return this.addressRepository.findById(addressId)
                .map(AddressMapping::map)
                .orElseThrow(() -> new AddressNotFoundException(String.format("Address with id: %d not found",addressId)));
    }

    @Override
    public AddressDTO save(AddressDTO addressDto) {
        log.info("AddressDTo, save the addresses");
        return AddressMapping.map(this.addressRepository.save(AddressMapping.map(addressDto)));
    }

    @Override
    public AddressDTO update(AddressDTO addressDto) {
        log.info("AddressDTO, Update the address");
        return AddressMapping.map(this.addressRepository.save(AddressMapping.map(addressDto)));
    }

    @Override
    public AddressDTO update(Integer addressId, AddressDTO addressDto) {
        log.info("AddressDTo, Update the address by the AddressId");
        return AddressMapping.map(this.addressRepository.save(AddressMapping.map(this.findById(addressId))));
    }

    @Override
    public void deleteById(Integer addressId) {
        log.info("Delete the address by AddressId");
        this.addressRepository.deleteById(addressId);

    }
}
