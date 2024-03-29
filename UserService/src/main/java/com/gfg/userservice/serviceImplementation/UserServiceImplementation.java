package com.gfg.userservice.serviceImplementation;

import com.gfg.userservice.dto.UserDTO;
import com.gfg.userservice.exceptions.UserObjectNotFoundException;
import com.gfg.userservice.helperClass.UserMapping;
import com.gfg.userservice.repository.UserRepository;
import com.gfg.userservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
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

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.info("**********Save user**************");
        return UserMapping.map(this.userRepository.save(UserMapping.map(userDTO)));
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
}
