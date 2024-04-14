package org.example.proxyclient.bussiness.order.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.proxyclient.bussiness.order.model.CartDTO;
import org.example.proxyclient.bussiness.order.model.response.CartOrderServiceDtoCollectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ORDER-SERVICE", contextId = "cartClientService", url = "http://localhost:9052/order-service/api/carts")
public interface CartClientService {

    @GetMapping
    public ResponseEntity<CartOrderServiceDtoCollectionResponse> findAll();

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> findById(
            @PathVariable("cartId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String cartId);

    @PostMapping
    public ResponseEntity<CartDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CartDTO cartDto);

    @PutMapping
    public ResponseEntity<CartDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CartDTO cartDto);

    @PutMapping("/{cartId}")
    public ResponseEntity<CartDTO> update(
            @PathVariable("cartId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String cartId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CartDTO cartDto);

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("cartId") final String cartId);

}
