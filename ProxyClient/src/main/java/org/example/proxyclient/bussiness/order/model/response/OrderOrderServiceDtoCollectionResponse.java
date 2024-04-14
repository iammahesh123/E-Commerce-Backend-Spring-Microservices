package org.example.proxyclient.bussiness.order.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proxyclient.bussiness.order.model.OrderDTO;

import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderOrderServiceDtoCollectionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Collection<OrderDTO> collection;

}
