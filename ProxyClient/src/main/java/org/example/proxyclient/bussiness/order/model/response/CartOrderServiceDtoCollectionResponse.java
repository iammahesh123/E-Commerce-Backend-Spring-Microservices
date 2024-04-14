package org.example.proxyclient.bussiness.order.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proxyclient.bussiness.order.model.CartDTO;

import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartOrderServiceDtoCollectionResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Collection<CartDTO> collection;

}
