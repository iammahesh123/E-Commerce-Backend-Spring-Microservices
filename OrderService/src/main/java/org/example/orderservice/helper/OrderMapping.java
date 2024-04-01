package org.example.orderservice.helper;

import org.example.orderservice.dtos.CartDTO;
import org.example.orderservice.dtos.OrderDTO;
import org.example.orderservice.ordermodel.Cart;
import org.example.orderservice.ordermodel.Order;

public interface OrderMapping {
    public static OrderDTO map(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .orderDesc(order.getOrderDesc())
                .cartDTO(CartDTO.builder()
                        .cartId(order.getCart().getCartId())
                        .build())
                .build();
    }

    public static Order map(OrderDTO orderDTO) {
        return Order.builder()
                .orderId(orderDTO.getOrderId())
                .orderDate(orderDTO.getOrderDate())
                .orderDesc(orderDTO.getOrderDesc())
                .cart(Cart.builder()
                        .cartId(orderDTO.getCartDTO().getCartId())
                        .build())
                .build();
    }
}
