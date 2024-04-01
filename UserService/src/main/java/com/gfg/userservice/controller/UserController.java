package com.gfg.userservice.controller;

import com.gfg.userservice.dto.UserDTO;
import com.gfg.userservice.response.ResponseCollectionDTO;
import com.gfg.userservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j

@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseCollectionDTO<UserDTO>> findAll() {
        log.info("UserDto List, controller; fetch all users");
        return ResponseEntity.ok(new ResponseCollectionDTO<>(this.userService.findAll()));

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findById(@PathVariable("userId")
                                            @NotBlank(message = "Input must not blank")
                                            @Valid final String userId) {
        log.info("UserDTO..resource; fetch user by id");
        return ResponseEntity.ok(this.userService.findById(Integer.parseInt(userId.strip())));
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody
                                        @NotNull(message = "Input must not null")
                                        @Valid final UserDTO userDTO) {
        log.info("*** UserDto, save the user *");
        return ResponseEntity.ok(this.userService.save(userDTO));

    }

    @PutMapping
    public ResponseEntity<UserDTO> update(
            @RequestBody
            @NotNull(message = "Input must not null")
            @Valid final UserDTO userDTO
    ) {
        log.info("*** UserDto, resource; update user *");
        return ResponseEntity.ok(this.userService.update(userDTO));

    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> update(
            @PathVariable("userId")
            @NotBlank(message = "Input must not blank") final String userId,
            @RequestBody
            @NotNull(message = "Input must not NULL")
            @Valid final UserDTO userDto) {
        log.info("*** UserDto, resource; update user with userId *");
        return ResponseEntity.ok(this.userService.update(Integer.parseInt(userId.strip()), userDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("userId") @NotBlank(message = "Input must not blank") @Valid final String userId) {
        log.info("*** Boolean, resource; delete user by id *");
        this.userService.deleteById(Integer.parseInt(userId));
        return ResponseEntity.ok(true);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> findByUsername(
            @PathVariable("username")
            @NotBlank(message = "Input must not blank")
            @Valid final String username) {
        return ResponseEntity.ok(this.userService.findByUsername(username));
    }

}
