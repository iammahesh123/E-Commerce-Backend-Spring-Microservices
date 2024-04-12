package org.example.favouriteservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String email;
    private String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<FavouriteDTO> favouriteDTOS;

}
