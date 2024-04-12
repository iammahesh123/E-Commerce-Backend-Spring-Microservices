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
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer productId;
    private String productTitle;
    private String imageUrl;
    private String sku;
    private Double priceUnit;
    private Integer quantity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<FavouriteDTO> favouriteDTOS;

}
