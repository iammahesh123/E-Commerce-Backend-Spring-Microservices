package com.gfg.userservice.helperClass;


import com.gfg.userservice.domain.Address;
import com.gfg.userservice.domain.User;
import com.gfg.userservice.dto.AddressDTO;
import com.gfg.userservice.dto.UserDTO;

public interface AddressMapping {
    public static AddressDTO map(final Address address) {
        return AddressDTO.builder()
                .addressId(address.getAddressId())
                .fullAddress(address.getFullAddress())
                .postalCode(address.getPostalCode())
                .city(address.getCity())
                .userDto(
                        UserDTO.builder()
                                .userId(address.getUser().getUserId())
                                .firstName(address.getUser().getFirstName())
                                .lastName(address.getUser().getLastName())
                                .imageUrl(address.getUser().getImageUrl())
                                .email(address.getUser().getEmail())
                                .phone(address.getUser().getPhone())
                                .build())
                .build();


    }
    public static Address map(final AddressDTO addressDto) {
        return Address.builder()
                .addressId(addressDto.getAddressId())
                .fullAddress(addressDto.getFullAddress())
                .postalCode(addressDto.getPostalCode())
                .city(addressDto.getCity())
                .user(
                        User.builder()
                                .userId(addressDto.getUserDto().getUserId())
                                .firstName(addressDto.getUserDto().getFirstName())
                                .lastName(addressDto.getUserDto().getLastName())
                                .imageUrl(addressDto.getUserDto().getImageUrl())
                                .email(addressDto.getUserDto().getEmail())
                                .phone(addressDto.getUserDto().getPhone())
                                .build())
                .build();
    }

}
