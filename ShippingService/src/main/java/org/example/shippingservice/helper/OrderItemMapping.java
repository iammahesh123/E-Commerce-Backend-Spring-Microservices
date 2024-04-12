package org.example.shippingservice.helper;

import org.example.shippingservice.domain.OrderItem;
import org.example.shippingservice.dtos.OrderDTO;
import org.example.shippingservice.dtos.OrderItemsDTO;
import org.example.shippingservice.dtos.ProductDTO;

public class OrderItemMapping {
    public static OrderItemsDTO map(final OrderItem orderItem) {
        return OrderItemsDTO.builder()
                .productId(orderItem.getProductId())
                .orderId(orderItem.getOrderId())
                .orderedQuantity(orderItem.getOrderedQuantity())
                .productDto(
                        ProductDTO.builder()
                                .productId(orderItem.getProductId())
                                .build())
                .orderDto(
                        OrderDTO.builder()
                                .orderId(orderItem.getOrderId())
                                .build())
                .build();
    }

    public static OrderItem map(final OrderItemsDTO orderItemDto) {
        return OrderItem.builder()
                .productId(orderItemDto.getProductId())
                .orderId(orderItemDto.getOrderId())
                .orderedQuantity(orderItemDto.getOrderedQuantity())
                .build();
    }
}
