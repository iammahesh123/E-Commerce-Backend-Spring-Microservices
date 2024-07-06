package org.example.shippingservice.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderItemsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productId;
    private Integer orderId;
    private Integer orderedQuantity;

    @JsonProperty("product")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProductDTO productDto;

    @JsonProperty("order")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderDTO orderDto;

}
