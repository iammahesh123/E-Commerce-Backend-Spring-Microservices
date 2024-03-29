package org.example.productservice.helper;

import org.example.productservice.domain.Category;
import org.example.productservice.domain.Product;
import org.example.productservice.dto.CategoryDTO;
import org.example.productservice.dto.ProductDTO;

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
