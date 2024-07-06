package org.example.shippingservice.mapper;

import org.example.shippingservice.domain.entity.OrderItem;
import org.example.shippingservice.domain.dtos.OrderDTO;
import org.example.shippingservice.domain.dtos.OrderItemsDTO;
import org.example.shippingservice.domain.dtos.ProductDTO;

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
