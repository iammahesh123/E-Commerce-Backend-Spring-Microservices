package org.example.productservice.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.productservice.dto.ProductDTO;
import org.example.productservice.exception.ProductNotFoundException;
import org.example.productservice.helper.ProductMapping;
import org.example.productservice.repository.ProductRepository;
import org.example.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private ProductRepository productRepository;
    @Override
    public List<ProductDTO> findAll() {
        log.info("ProductDTO, Fetch all the products");
        return this.productRepository.findAll()
                .stream().map(ProductMapping::map).distinct().collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Integer productId) {
        log.info("ProductDTO, Fetch the products using productId");
        return this.productRepository.findById(productId)
                .map(ProductMapping::map)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id: %d not found", productId)));

    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.info("ProductDTO, save the products");
        return ProductMapping.map(this.productRepository.save(ProductMapping.map(productDTO)));
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        log.info("ProductDTO, update the products");
        return ProductMapping.map(
                this.productRepository.save(ProductMapping.map(productDTO))
        );
    }

    @Override
    public ProductDTO update(Integer productId, ProductDTO productDTO) {
        log.info("ProductDTO, update the products by using productId");
        return ProductMapping.map(this.productRepository.save(ProductMapping.map(this.findById(productId))));
    }

    @Override
    public void deleteById(Integer productId) {
        log.info("ProductDTO, update the products");
        this.productRepository.deleteById(productId);

    }
}
