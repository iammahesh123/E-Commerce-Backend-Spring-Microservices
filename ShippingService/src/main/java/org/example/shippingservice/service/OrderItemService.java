package org.example.shippingservice.service;

import org.example.shippingservice.domain.OrderItemId;
import org.example.shippingservice.dtos.OrderItemsDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemsDTO> findAll();
    OrderItemsDTO findById(final OrderItemId orderItemId);
    OrderItemsDTO save(final OrderItemsDTO orderItemDto);
    OrderItemsDTO update(final OrderItemsDTO orderItemDto);
    void deleteById(final OrderItemId orderItemId);
}
