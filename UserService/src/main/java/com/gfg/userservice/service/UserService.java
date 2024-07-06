package com.gfg.userservice.service;

import com.gfg.userservice.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();
    UserDTO findById(final Integer userId);
    UserDTO save(final UserDTO userDTO);
    UserDTO update(final UserDTO userDTO);
    UserDTO update(final Integer userId, final UserDTO userDTO);
    void deleteById(final Integer userId);
    UserDTO findByUsername(final String username);

    void activateAccount(String email);
    void forgotPassword(String email);
    void resetPassword(String token, String newPassword);

}
