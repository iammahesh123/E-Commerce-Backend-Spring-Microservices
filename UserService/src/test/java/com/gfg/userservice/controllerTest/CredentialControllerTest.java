package com.gfg.userservice.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.userservice.controller.CredentialController;
import com.gfg.userservice.domain.dto.CredentialDTO;
import com.gfg.userservice.service.CredentialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CredentialControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CredentialService credentialService;

    @InjectMocks
    private CredentialController credentialController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(credentialController).build();
    }

//    @Test
//    public void testFindAllCredentials() throws Exception {
//        CredentialDTO credentialDTO = createCredentialDTO(); // Create a sample credential DTO
//        when(credentialService.findAll()).thenReturn(Collections.singletonList(credentialDTO));
//
//        mockMvc.perform(get("/api/credential"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.credentialId").value(credentialDTO.getCredentialId()))
//                .andExpect(jsonPath("$.username").value(credentialDTO.getUsername()));
//    }
//
//
//
//    @Test
//    public void testFindCredentialById() throws Exception {
//        CredentialDTO credentialDTO = createCredentialDTO(); // Create a sample credential DTO
//        int credentialId = 1;
//        when(credentialService.findById(credentialId)).thenReturn(credentialDTO);
//
//        mockMvc.perform(get("/api/credential/{credentialId}", credentialId))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.credentialId").value(credentialDTO.getCredentialId()))
//                .andExpect(jsonPath("$.username").value(credentialDTO.getUsername()));
//    }


    @Test
    public void testSaveCredential() throws Exception {
        CredentialDTO credentialDTO = createCredentialDTO(); // Create a sample credential DTO
        when(credentialService.save(any(CredentialDTO.class))).thenReturn(credentialDTO);

        mockMvc.perform(post("/api/credential")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(credentialDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.credentialId").value(credentialDTO.getCredentialId()))
                .andExpect(jsonPath("$.username").value(credentialDTO.getUsername()));
    }

    @Test
    public void testUpdateCredential() throws Exception {
        CredentialDTO credentialDTO = createCredentialDTO(); // Create a sample credential DTO
        when(credentialService.update(any(CredentialDTO.class))).thenReturn(credentialDTO);

        mockMvc.perform(put("/api/credential")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(credentialDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.credentialId").value(credentialDTO.getCredentialId()))
                .andExpect(jsonPath("$.username").value(credentialDTO.getUsername()));
    }

    @Test
    public void testUpdateCredentialById() throws Exception {
        CredentialDTO credentialDTO = createCredentialDTO(); // Create a sample credential DTO
        int credentialId = 1;
        when(credentialService.update(eq(credentialId), any(CredentialDTO.class))).thenReturn(credentialDTO);

        mockMvc.perform(put("/api/credential/{credentialId}", credentialId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(credentialDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.credentialId").value(credentialDTO.getCredentialId()))
                .andExpect(jsonPath("$.username").value(credentialDTO.getUsername()));
    }

    @Test
    public void testDeleteCredentialById() throws Exception {
        mockMvc.perform(delete("/api/credential/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(credentialService, times(1)).deleteById(1);
    }

    @Test
    public void testFindByUsername() throws Exception {
        CredentialDTO credentialDTO = createCredentialDTO(); // Create a sample credential DTO
        String username = "john.doe";
        when(credentialService.findByUsername(username)).thenReturn(credentialDTO);

        mockMvc.perform(get("/api/credential/username/{username}", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.credentialId").value(credentialDTO.getCredentialId()))
                .andExpect(jsonPath("$.username").value(credentialDTO.getUsername()));
    }

    // Helper method to create a sample CredentialDTO
    private CredentialDTO createCredentialDTO() {
        return CredentialDTO.builder()
                .credentialId(1)
                .username("maheshkadambala18")
                .password("Mahesh@123")
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialNonExpired(true)
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
