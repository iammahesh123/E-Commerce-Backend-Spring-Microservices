package org.example.orderservice.service;

import org.example.orderservice.domain.dtos.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> findAll();
    OrderDTO findById(Integer orderId);
    OrderDTO save(OrderDTO orderDTO);

    OrderDTO update(OrderDTO orderDTO);
    OrderDTO update(Integer orderId,OrderDTO orderDTO);
    void deleteById(Integer orderId);
}
