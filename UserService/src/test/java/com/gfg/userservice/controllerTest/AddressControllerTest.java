package com.gfg.userservice.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.userservice.controller.AddressController;
import com.gfg.userservice.domain.dto.AddressDTO;
import com.gfg.userservice.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }


//    @Test
//    public void testFindAllAddresses() throws Exception {
//        AddressDTO addressDTO = createAddressDTO(); // Create a sample address DTO
//        when(addressService.findAll()).thenReturn(Collections.singletonList(addressDTO));
//
//        mockMvc.perform(get("/api/address"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data[0].addressId").value(addressDTO.getAddressId()))
//                .andExpect(jsonPath("$.data[0].fullAddress").value(addressDTO.getFullAddress()));
//    }

    @Test
    public void testFindAddressById() throws Exception {
        AddressDTO addressDTO = createAddressDTO(); // Create a sample address DTO
        int addressId = 1;
        when(addressService.findById(addressId)).thenReturn(addressDTO);

        mockMvc.perform(get("/api/address/{addressId}", addressId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(addressDTO.getAddressId()))
                .andExpect(jsonPath("$.fullAddress").value(addressDTO.getFullAddress()));
    }

    @Test
    public void testSaveAddress() throws Exception {
        AddressDTO addressDTO = createAddressDTO(); // Create a sample address DTO
        when(addressService.save(any(AddressDTO.class))).thenReturn(addressDTO);

        mockMvc.perform(post("/api/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(addressDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(addressDTO.getAddressId()))
                .andExpect(jsonPath("$.fullAddress").value(addressDTO.getFullAddress()));
    }

    @Test
    public void testUpdateAddress() throws Exception {
        AddressDTO addressDTO = createAddressDTO(); // Create a sample address DTO
        when(addressService.update(any(AddressDTO.class))).thenReturn(addressDTO);

        mockMvc.perform(put("/api/address/{addressId}", addressDTO.getAddressId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(addressDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(addressDTO.getAddressId()))
                .andExpect(jsonPath("$.fullAddress").value(addressDTO.getFullAddress()));
    }

//    @Test
//    public void testDeleteAddressById() throws Exception {
//        mockMvc.perform(delete("/api/address/{addressId}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//
//        verify(addressService, times(1)).deleteById(1);
//    }

    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Helper method to create a sample AddressDTO
    private AddressDTO createAddressDTO() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressId(1);
        addressDTO.setFullAddress("Sample Address");
        addressDTO.setPostalCode("12345");
        addressDTO.setCity("Sample City");

        // Optionally, set userDTO if it's included in your AddressDTO
        // addressDTO.setUserDto(createUserDTO());

        return addressDTO;
    }
}

