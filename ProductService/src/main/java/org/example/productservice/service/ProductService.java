package org.example.productservice.service;

import org.example.productservice.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(final Integer productId);
    ProductDTO save(final ProductDTO productDTO);
    ProductDTO update(final ProductDTO productDTO);
    ProductDTO update(final Integer productId,final ProductDTO productDTO);
    void deleteById(final Integer productId);
}
