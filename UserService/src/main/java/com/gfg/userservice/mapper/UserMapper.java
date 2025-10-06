package com.gfg.userservice.mapper;

import com.gfg.userservice.domain.dto.AddressDTO;
import com.gfg.userservice.domain.dto.CredentialDTO;
import com.gfg.userservice.domain.entity.User;
import com.gfg.userservice.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDTO toDTO(final User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        if(user.getCredential() != null) {
            userDTO.setCredentialDTO(modelMapper.map(user.getCredential(), CredentialDTO.class));
        }
        if (user.getAddresses() != null) {
            userDTO.setAddressDTOs(Collections.singleton(modelMapper.map(user.getAddresses(), AddressDTO.class)));
        }
        return userDTO;
    }

    public User toEntity(final UserDTO userDto) {
        if (userDto == null) {
            return null;
        }
        User user = modelMapper.map(userDto, User.class);
        if (user.getCredential() != null) {
            user.getCredential().setUser(user);
        }
        return user;
    }
}
