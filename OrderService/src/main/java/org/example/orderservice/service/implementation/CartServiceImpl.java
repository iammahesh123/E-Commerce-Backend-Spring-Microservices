package org.example.orderservice.service.implementation;

import org.example.orderservice.dtos.CartDTO;
import org.example.orderservice.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    @Override
    public List<CartDTO> findAll() {
        return null;
    }

    @Override
    public CartDTO findById(Integer orderId) {
        return null;
    }

    @Override
    public CartDTO save(CartDTO cartDTO) {
        return null;
    }

    @Override
    public CartDTO update(CartDTO cartDTO) {
        return null;
    }

    @Override
    public CartDTO update(Integer cartId, CartDTO cartDTO) {
        return null;
    }

    @Override
    public void deleteById(Integer cartId) {

    }
}
