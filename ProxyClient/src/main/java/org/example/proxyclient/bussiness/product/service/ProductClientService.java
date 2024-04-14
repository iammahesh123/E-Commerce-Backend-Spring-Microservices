package org.example.proxyclient.bussiness.product.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.proxyclient.bussiness.product.model.ProductDTO;
import org.example.proxyclient.bussiness.product.model.response.ProductProductServiceCollectionDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PRODUCT-SERVICE", contextId = "productClientService", url = "http://localhost:9051/product-service/api/products")
public interface ProductClientService {

    @GetMapping
    ResponseEntity<ProductProductServiceCollectionDtoResponse> findAll();

    @GetMapping("/{productId}")
    ResponseEntity<ProductDTO> findById(
            @PathVariable("productId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String productId);

    @PostMapping
    ResponseEntity<ProductDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final ProductDTO productDto);

    @PutMapping
    ResponseEntity<ProductDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final ProductDTO productDto);

    @PutMapping("/{productId}")
    ResponseEntity<ProductDTO> update(
            @PathVariable("productId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String productId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final ProductDTO productDto);

    @DeleteMapping("/{productId}")
    ResponseEntity<Boolean> deleteById(@PathVariable("productId") final String productId);

}
