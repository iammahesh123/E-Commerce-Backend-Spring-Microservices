package com.gfg.userservice.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.userservice.controller.UserController;
import com.gfg.userservice.dto.UserDTO;
import com.gfg.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testSaveUser() throws Exception {
        UserDTO userDTO = createUserDTO(); // Create a sample user DTO
        when(userService.save(any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userDTO.getUserId()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()));
    }
    @Test
    public void testUpdateUser() throws Exception {
        UserDTO userDTO = createUserDTO(); // Create a sample user DTO
        when(userService.update(any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userDTO.getUserId()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()));
    }

    @Test
    public void testDeleteUserById() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(userService, times(1)).deleteById(1);
    }
    @Test
    public void testUpdateUserById() throws Exception {
        UserDTO userDTO = createUserDTO(); // Create a sample user DTO
        int userId = 1;
        when(userService.update(eq(userId), any(UserDTO.class))).thenReturn(userDTO);

        mockMvc.perform(put("/api/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userDTO.getUserId()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()));
    }
    @Test
    public void testFindUserById() throws Exception {
        UserDTO userDTO = createUserDTO(); // Create a sample user DTO
        int userId = 1;
        when(userService.findById(userId)).thenReturn(userDTO);

        mockMvc.perform(get("/api/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userDTO.getUserId()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()));
    }



    // Helper method to create a sample UserDTO
    private UserDTO createUserDTO() {
        return UserDTO.builder()
                .userId(1)
                .firstName("Mahesh")
                .lastName("Kadambala")
                .email("maheshkadambala18@gmail.com")
                .build();
    }

    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
