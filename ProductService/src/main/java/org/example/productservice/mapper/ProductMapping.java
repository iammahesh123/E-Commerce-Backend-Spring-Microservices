package org.example.productservice.mapper;

import org.example.productservice.domain.entity.Category;
import org.example.productservice.domain.entity.Product;
import org.example.productservice.domain.dto.CategoryDTO;
import org.example.productservice.domain.dto.ProductDTO;

public interface ProductMapping {

    public static ProductDTO map(final Product product) {
        return ProductDTO.builder().
                productId(product.getProductId())
                .productTitle(product.getProductName())
                .imageUrl(product.getProductImageUrl())
                .sku(product.getSku())
                .priceUnit(product.getPrice())
                .quantity(product.getQuantity())
                .categoryDto(CategoryDTO.builder()
                        .categoryId(product.getCategory().getCategoryId())
                        .categoryName(product.getCategory().getCategoryName())
                        .categoryImageUrl(product.getCategory().getCategoryImageUrl())
                        .build())

                .build();
    }

    public static Product map(final ProductDTO productDTO) {
        return Product.builder()
                .productId(productDTO.getProductId())
                .productName(productDTO.getProductTitle())
                .productImageUrl(productDTO.getImageUrl())
                .sku(productDTO.getSku())
                .quantity(productDTO.getQuantity())
                .category(Category.builder()
                        .categoryId(productDTO.getCategoryDto().getCategoryId())
                        .categoryName(productDTO.getCategoryDto().getCategoryName())
                        .categoryImageUrl(productDTO.getCategoryDto().getCategoryImageUrl())
                        .build())


                .build();
    }
}
