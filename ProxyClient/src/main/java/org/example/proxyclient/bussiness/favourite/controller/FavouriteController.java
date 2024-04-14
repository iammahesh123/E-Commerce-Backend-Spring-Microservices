package org.example.proxyclient.bussiness.favourite.controller;

import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.favourite.model.FavouriteDTO;
import org.example.proxyclient.bussiness.favourite.model.FavouriteId;
import org.example.proxyclient.bussiness.favourite.model.response.FavouriteFavouriteServiceCollectionDtoResponse;
import org.example.proxyclient.bussiness.favourite.service.FavouriteClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favourites")
@RequiredArgsConstructor
public class FavouriteController {

    private final FavouriteClientService favouriteClientService;

    @GetMapping
    public ResponseEntity<FavouriteFavouriteServiceCollectionDtoResponse> findAll() {
        return ResponseEntity.ok(this.favouriteClientService.findAll().getBody());
    }

    @GetMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<FavouriteDTO> findById(
            @PathVariable("userId") final String userId,
            @PathVariable("productId") final String productId,
            @PathVariable("likeDate") final String likeDate) {
        return ResponseEntity.ok(this.favouriteClientService.findById(userId, productId, likeDate).getBody());
    }

    @GetMapping("/find")
    public ResponseEntity<FavouriteDTO> findById(@RequestBody final FavouriteId favouriteId) {
        return ResponseEntity.ok(this.favouriteClientService.findById(favouriteId).getBody());
    }

    @PostMapping
    public ResponseEntity<FavouriteDTO> save(@RequestBody final FavouriteDTO favouriteDto) {
        return ResponseEntity.ok(this.favouriteClientService.save(favouriteDto).getBody());
    }

    @PutMapping
    public ResponseEntity<FavouriteDTO> update(@RequestBody final FavouriteDTO favouriteDto) {
        return ResponseEntity.ok(this.favouriteClientService.update(favouriteDto).getBody());
    }

    @DeleteMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("userId") final String userId,
            @PathVariable("productId") final String productId,
            @PathVariable("likeDate") final String likeDate) {
        this.favouriteClientService.deleteById(userId, productId, likeDate).getBody();
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(@RequestBody final FavouriteId favouriteId) {
        this.favouriteClientService.deleteById(favouriteId).getBody();
        return ResponseEntity.ok(true);
    }



}
