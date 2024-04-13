package org.example.proxyclient.bussiness.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressDTO {

    private static final long serialVersionUID = 1L;

    private Integer addressId;

    private String fullAddress;

    private String postalCode;

    private String city;

    @JsonProperty("user")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private UserDTO userDto;

}
