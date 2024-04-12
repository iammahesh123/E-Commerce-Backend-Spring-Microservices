package org.example.favouriteservice.service;

import org.example.favouriteservice.dtos.FavouriteDTO;
import org.example.favouriteservice.model.FavouriteId;

import java.util.List;

public interface FavouriteService {
    List<FavouriteDTO> findAll();
    FavouriteDTO findById(final FavouriteId favouriteId);
    FavouriteDTO save(final FavouriteDTO favouriteDTO);
    FavouriteDTO update(final FavouriteDTO favouriteDTO);
    void deleteById(final FavouriteId favouriteId);
}
