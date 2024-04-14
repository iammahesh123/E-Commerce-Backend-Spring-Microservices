package org.example.proxyclient.bussiness.product.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.proxyclient.bussiness.product.model.CategoryDTO;
import org.example.proxyclient.bussiness.product.model.response.CategoryProductServiceCollectionDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PRODUCT-SERVICE", contextId = "categoryClientService", url = "http://localhost:9051/product-service/api/categories")
public interface CategoryClientService {

    @GetMapping
    ResponseEntity<CategoryProductServiceCollectionDtoResponse> findAll();

    @GetMapping("/{categoryId}")
    ResponseEntity<CategoryDTO> findById(
            @PathVariable("categoryId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String categoryId);

    @PostMapping
    ResponseEntity<CategoryDTO> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CategoryDTO categoryDto);

    @PutMapping
    ResponseEntity<CategoryDTO> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CategoryDTO categoryDto);

    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryDTO> update(
            @PathVariable("categoryId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String categoryId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final CategoryDTO categoryDto);

    @DeleteMapping("/{categoryId}")
    ResponseEntity<Boolean> deleteById(@PathVariable("categoryId") final String categoryId);

}
