package org.example.proxyclient.bussiness.favourite.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.proxyclient.bussiness.favourite.model.FavouriteDTO;
import org.example.proxyclient.bussiness.favourite.model.FavouriteId;
import org.example.proxyclient.bussiness.favourite.model.response.FavouriteFavouriteServiceCollectionDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "FAVOURITE-SERVICE", contextId = "favouriteClientService", path = "/favourite-service/api/favourites")
public interface FavouriteClientService {

    @GetMapping
    ResponseEntity<FavouriteFavouriteServiceCollectionDtoResponse> findAll();

    @GetMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<FavouriteDTO> findById(
            @PathVariable("userId") final String userId,
            @PathVariable("productId") final String productId,
            @PathVariable("likeDate") final String likeDate);

    @GetMapping("/find")
    public ResponseEntity<FavouriteDTO> findById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteId favouriteId);

    @PostMapping
    public ResponseEntity<FavouriteDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteDTO favouriteDto);

    @PutMapping
    public ResponseEntity<FavouriteDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteDTO favouriteDto);

    @DeleteMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("userId") final String userId,
            @PathVariable("productId") final String productId,
            @PathVariable("likeDate") final String likeDate);

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteId favouriteId);

}
