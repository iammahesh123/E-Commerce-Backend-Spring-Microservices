package com.gfg.userservice.service;

import com.gfg.userservice.domain.User;
import com.gfg.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();
    UserDTO findById(final Integer userId);
    UserDTO save(final UserDTO userDTO);
    UserDTO update(final UserDTO userDTO);
    UserDTO update(final Integer userId, final UserDTO userDTO);
    void deleteById(final Integer userId);
    UserDTO findByUsername(final String username);
}
