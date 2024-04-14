package org.example.proxyclient.bussiness.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.proxyclient.bussiness.product.model.ProductDTO;
import org.example.proxyclient.bussiness.product.model.response.ProductProductServiceCollectionDtoResponse;
import org.example.proxyclient.bussiness.product.service.ProductClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductClientService productClientService;

    @GetMapping
    public ResponseEntity<ProductProductServiceCollectionDtoResponse> findAll() {
        return ResponseEntity.ok(this.productClientService.findAll().getBody());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("productId") final String productId) {
        return ResponseEntity.ok(this.productClientService.findById(productId).getBody());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody final ProductDTO productDto) {
        return ResponseEntity.ok(this.productClientService.save(productDto).getBody());
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody final ProductDTO productDto) {
        return ResponseEntity.ok(this.productClientService.update(productDto).getBody());
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> update(@PathVariable("productId") final String productId,
                                             @RequestBody final ProductDTO productDto) {
        return ResponseEntity.ok(this.productClientService.update(productId, productDto).getBody());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("productId") final String productId) {
        return ResponseEntity.ok(this.productClientService.deleteById(productId).getBody());
    }
}

