package com.gfg.userservice.service.serviceImpl;

import com.gfg.userservice.domain.dto.UserDTO;
import com.gfg.userservice.domain.entity.User;
import com.gfg.userservice.exceptions.UserObjectNotFoundException;
import com.gfg.userservice.mapper.UserMapper;
import com.gfg.userservice.repository.CredentialRepository;
import com.gfg.userservice.repository.UserRepository;
import com.gfg.userservice.security.JwtUtil;
import com.gfg.userservice.service.EmailService;
import com.gfg.userservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<UserDTO> findAll() {
        log.info("*****find all user*****");
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    @Override
    public UserDTO findById(Integer userId) {
        log.info("*****find by id*******");
        User user = userRepository.findById(userId).orElseThrow(() -> new UserObjectNotFoundException(String.format("User not found with is id: %d",userId)));
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO save(final UserDTO userDto) {
        log.info("*** UserDto, service; save user *");
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        log.info("************update the user******************");
        return userMapper.toDTO(this.userRepository.save(userMapper.toEntity(userDTO)));

    }

    @Override
    public UserDTO update(Integer userId, UserDTO userDTO) {
        log.info("*** UserDto, service; update user with userId *");
        BeanUtils.copyProperties(userDTO, this.findById(userId), "id", "credential", "addresses");
        User updatedUser = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteById(Integer userId) {
        log.info("*** Void, service; delete user by id *");
        this.userRepository.deleteById(userId);
    }

    @Override
    public UserDTO findByUsername(String username) {
        log.info("*** UserDto, service; fetch user with username *");
        return userMapper.toDTO(this.userRepository.findByCredentialUsername(username)
                .orElseThrow(() -> new UserObjectNotFoundException(String.format("User with username: %s not found", username))));
    }

    @Override
    public void activateAccount(String email) {
        String activationToken = generateActivationToken(email);
        emailService.sendSimpleMessage(email, "Account Activation", "Your activation token: " + activationToken);
    }

    @Override
    public void forgotPassword(String email) {
        String resetToken = generateResetToken(email);
        emailService.sendSimpleMessage(email, "Password Reset", "Your password reset token: " + resetToken);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        validateResetToken(token);
        updatePassword(token, newPassword);
    }

    private String generateActivationToken(String email) {
        return jwtUtil.generateToken(email);
    }

    private String generateResetToken(String email) {
        return jwtUtil.generateToken(email);
    }

    private void validateResetToken(String token) {
        String username = jwtUtil.extractUsername(token);
        if (username == null || !jwtUtil.validateToken(token, username)) {
            throw new IllegalArgumentException("Invalid or expired token");
        }
    }

    private void updatePassword(String token, String newPassword) {
        String username = jwtUtil.extractUsername(token);
        User user = userRepository.findByCredentialUsername(username)
                .orElseThrow(() -> new UserObjectNotFoundException("User not found"));
        user.getCredential().setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
