package org.example.favouriteservice.helper;

import org.example.favouriteservice.dtos.FavouriteDTO;
import org.example.favouriteservice.dtos.ProductDTO;
import org.example.favouriteservice.dtos.UserDTO;
import org.example.favouriteservice.model.Favourite;

public interface FavouriteMapping {
    public static FavouriteDTO map(final Favourite favourite) {
        return FavouriteDTO.builder()
                .userId(favourite.getUserId())
                .productId(favourite.getProductId())
                .likeDate(favourite.getLikeDate())
                .userDTO(
                        UserDTO.builder()
                                .userId(favourite.getUserId())
                                .build())
                .productDTO(
                        ProductDTO.builder()
                                .productId(favourite.getProductId())
                                .build())
                .build();
    }

    public static Favourite map(final FavouriteDTO favouriteDTO) {
        return Favourite.builder()
                .userId(favouriteDTO.getUserId())
                .productId(favouriteDTO.getProductId())
                .likeDate(favouriteDTO.getLikeDate())
                .build();
    }



}
