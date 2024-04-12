package org.example.favouriteservice.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.favouriteservice.constant.AppConstant;
import org.example.favouriteservice.dtos.FavouriteDTO;
import org.example.favouriteservice.model.FavouriteId;
import org.example.favouriteservice.response.DTOCollectionResponse;
import org.example.favouriteservice.service.FavouriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/favourites")
@Slf4j
@RequiredArgsConstructor
public class FavouriteController {

    private final FavouriteService favouriteService;

    @GetMapping
    public ResponseEntity<DTOCollectionResponse<FavouriteDTO>> findAll() {
        log.info("*** FavouriteDto List, controller; fetch all favourites *");
        return ResponseEntity.ok(new DTOCollectionResponse<>(this.favouriteService.findAll()));
    }

    @GetMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<FavouriteDTO> findById(
            @PathVariable("userId") final String userId,
            @PathVariable("productId") final String productId,
            @PathVariable("likeDate") final String likeDate) {
        log.info("*** FavouriteDto, resource; fetch favourite by id *");
        return ResponseEntity.ok(this.favouriteService.findById(
                new FavouriteId(Integer.parseInt(userId), Integer.parseInt(productId),
                        LocalDateTime.parse(likeDate, DateTimeFormatter.ofPattern(AppConstant.LOCAL_DATE_TIME_FORMAT)))));
    }

    @GetMapping("/find")
    public ResponseEntity<FavouriteDTO> findById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteId favouriteId) {
        log.info("*** FavouriteDto, resource; fetch favourite by id *");
        return ResponseEntity.ok(this.favouriteService.findById(favouriteId));
    }

    @PostMapping
    public ResponseEntity<FavouriteDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteDTO favouriteDto) {
        log.info("*** FavouriteDto, resource; save favourite *");
        return ResponseEntity.ok(this.favouriteService.save(favouriteDto));
    }

    @PutMapping
    public ResponseEntity<FavouriteDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteDTO favouriteDTO) {
        log.info("*** FavouriteDto, resource; update favourite *");
        return ResponseEntity.ok(this.favouriteService.update(favouriteDTO));
    }

    @DeleteMapping("/{userId}/{productId}/{likeDate}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("userId") final String userId,
            @PathVariable("productId") final String productId,
            @PathVariable("likeDate") final String likeDate) {
        log.info("*** Boolean, resource; delete favourite by id *");
        this.favouriteService.deleteById(new FavouriteId(Integer.parseInt(userId), Integer.parseInt(productId),
                LocalDateTime.parse(likeDate, DateTimeFormatter.ofPattern(AppConstant.LOCAL_DATE_TIME_FORMAT))));
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteById(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final FavouriteId favouriteId) {
        log.info("*** Boolean, resource; delete favourite by id *");
        this.favouriteService.deleteById(favouriteId);
        return ResponseEntity.ok(true);
    }
}
