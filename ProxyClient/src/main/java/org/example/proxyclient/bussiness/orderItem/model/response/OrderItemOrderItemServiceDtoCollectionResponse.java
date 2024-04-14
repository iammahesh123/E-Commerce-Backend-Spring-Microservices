package org.example.proxyclient.bussiness.orderItem.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proxyclient.bussiness.orderItem.model.OrderItemDTO;

import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderItemOrderItemServiceDtoCollectionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Collection<OrderItemDTO> collection;

}