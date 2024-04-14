package org.example.proxyclient.bussiness.orderItem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer productId;
    private Integer orderId;

}
