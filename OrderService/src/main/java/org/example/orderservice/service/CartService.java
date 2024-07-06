package org.example.orderservice.service;

import org.example.orderservice.domain.dtos.CartDTO;

import java.util.List;

public interface CartService {
    List<CartDTO> findAll();
    CartDTO findById(Integer orderId);

    CartDTO save(CartDTO cartDTO);
    CartDTO update(CartDTO cartDTO);
    CartDTO update(Integer cartId, CartDTO cartDTO);

    void deleteById(Integer cartId);
}
