package org.example.proxyclient.bussiness.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.user.model.UserDTO;
import org.example.proxyclient.bussiness.user.model.response.UserUserServiceCollectionDtoResponse;
import org.example.proxyclient.bussiness.user.service.UserClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserClientService userClientService;

    @GetMapping
    public ResponseEntity<UserUserServiceCollectionDtoResponse> findAll() {
        try {
            return ResponseEntity.ok(this.userClientService.findAll().getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserUserServiceCollectionDtoResponse(e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> findById(@PathVariable("userId") final String userId) {
        try {
            return ResponseEntity.ok(this.userClientService.findById(userId).getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserDTO(e.getMessage()));
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable("username") final String username) {
        try {
            return ResponseEntity.ok(this.userClientService.findByUsername(username).getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody final UserDTO userDto) {
        try {
            ResponseEntity<UserDTO> response = this.userClientService.save(userDto);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserDTO(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody final UserDTO userDto) {
        try {
            return ResponseEntity.ok(this.userClientService.update(userDto).getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserDTO(e.getMessage()));
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> update(@PathVariable("userId") final String userId, @RequestBody final UserDTO userDto) {
        try {
            return ResponseEntity.ok(this.userClientService.update(userDto).getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("userId") final String userId) {
        try {
            return ResponseEntity.ok(this.userClientService.deleteById(userId).getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(false);
        }
    }
}

