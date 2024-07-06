package com.gfg.userservice.service.serviceImpl;

import com.gfg.userservice.domain.dto.UserDTO;
import com.gfg.userservice.domain.entity.Credential;
import com.gfg.userservice.domain.entity.User;
import com.gfg.userservice.exceptions.UserObjectNotFoundException;
import com.gfg.userservice.helperClass.UserMapping;
import com.gfg.userservice.repository.CredentialRepository;
import com.gfg.userservice.repository.UserRepository;
import com.gfg.userservice.security.JwtUtil;
import com.gfg.userservice.service.EmailService;
import com.gfg.userservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CredentialRepository credentialRepository;
    @Override
    public List<UserDTO> findAll() {
        log.info("*****find all user*****");

        return this.userRepository.findAll()
                .stream().map(UserMapping::map)
                .distinct().collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Integer userId) {
        log.info("*****find by id*******");
        return this.userRepository.findById(userId)
                .map(UserMapping::map)
                .orElseThrow(() -> new UserObjectNotFoundException(String.format("User not found with is id: %d",userId)));
    }


//    @Override
//    public UserDTO save(final UserDTO userDto) {
//        log.info("*** UserDto, service; save user *");
//        return UserMapping.map(this.userRepository.save(UserMapping.map(userDto)));
//    }


    @Override
    public UserDTO save(final UserDTO userDto) {
        log.info("*** UserDto, service; save user *");
        return UserMapping.map(this.userRepository.save(UserMapping.map(userDto)));
    }



    @Override
    public UserDTO update(UserDTO userDTO) {
        log.info("************update the user******************");
        return UserMapping.map(this.userRepository.save(UserMapping.map(userDTO)));

    }

    @Override
    public UserDTO update(Integer userId, UserDTO userDTO) {
        log.info("*** UserDto, service; update user with userId *");
        return UserMapping.map(this.userRepository.save(
                UserMapping.map(this.findById(userId))));
    }

    @Override
    public void deleteById(Integer userId) {
        log.info("*** Void, service; delete user by id *");
        this.userRepository.deleteById(userId);
    }

    @Override
    public UserDTO findByUsername(String username) {
        log.info("*** UserDto, service; fetch user with username *");
        return UserMapping.map(this.userRepository.findByCredentialUsername(username)
                .orElseThrow(() -> new UserObjectNotFoundException(String.format("User with username: %s not found", username))));
    }

    private final EmailService emailService;
    private final JwtUtil jwtUtil;

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
        user.getCredential().setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }


}
