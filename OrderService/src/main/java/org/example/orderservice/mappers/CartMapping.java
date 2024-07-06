package org.example.orderservice.mappers;

import org.example.orderservice.domain.dtos.CartDTO;
import org.example.orderservice.domain.dtos.UserDTO;
import org.example.orderservice.domain.entity.Cart;

public interface CartMapping {

    public static CartDTO map(Cart cart) {

        return CartDTO.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUserId())
                .userDTO(UserDTO.builder()
                        .userId(cart.getUserId())
                        .build())
                .build();
    }

    public static Cart map(CartDTO cartDTO) {
        return Cart.builder()
                .cartId(cartDTO.getCartId())
                .userId(cartDTO.getUserId())
                .build();
    }
}
