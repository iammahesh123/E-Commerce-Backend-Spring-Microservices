package org.example.proxyclient.bussiness.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.product.model.CategoryDTO;
import org.example.proxyclient.bussiness.product.model.response.CategoryProductServiceCollectionDtoResponse;
import org.example.proxyclient.bussiness.product.service.CategoryClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryClientService categoryClientService;

    @GetMapping
    public ResponseEntity<CategoryProductServiceCollectionDtoResponse> findAll() {
        return ResponseEntity.ok(this.categoryClientService.findAll().getBody());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable("categoryId") final String categoryId) {
        return ResponseEntity.ok(this.categoryClientService.findById(categoryId).getBody());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody final CategoryDTO categoryDto) {
        return ResponseEntity.ok(this.categoryClientService.save(categoryDto).getBody());
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestBody final CategoryDTO categoryDto) {
        return ResponseEntity.ok(this.categoryClientService.update(categoryDto).getBody());
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("categoryId") final String categoryId,
                                              @RequestBody final CategoryDTO categoryDto) {
        return ResponseEntity.ok(this.categoryClientService.update(categoryId, categoryDto).getBody());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("categoryId") final String categoryId) {
        return ResponseEntity.ok(this.categoryClientService.deleteById(categoryId).getBody());
    }
}
