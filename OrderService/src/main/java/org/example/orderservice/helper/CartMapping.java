package org.example.orderservice.helper;

import org.example.orderservice.dtos.CartDTO;
import org.example.orderservice.dtos.UserDTO;
import org.example.orderservice.ordermodel.Cart;

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
