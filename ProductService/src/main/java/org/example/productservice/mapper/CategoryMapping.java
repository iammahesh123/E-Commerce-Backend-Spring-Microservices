package org.example.productservice.mapper;

import org.example.productservice.domain.entity.Category;
import org.example.productservice.domain.dto.CategoryDTO;

import java.util.Optional;

public interface CategoryMapping {

    public static CategoryDTO map(final Category category) {
        final var parentCategory = Optional.ofNullable(category
                .getParentCategory()).orElseGet(() -> new Category());

        return CategoryDTO.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryImageUrl(category.getCategoryImageUrl())
                .parentCategoryDTO(
                        CategoryDTO.builder()
                                .categoryId(parentCategory.getCategoryId())
                                .categoryName(parentCategory.getCategoryName())
                                .categoryImageUrl(parentCategory.getCategoryImageUrl())
                                .build())


                .build();


    }

    public static Category map(final CategoryDTO categoryDTO) {
        final var parentCategoryDto = Optional.ofNullable(categoryDTO.getParentCategoryDTO()).orElseGet(() -> new CategoryDTO());

        return Category.builder()
                .categoryId(categoryDTO.getCategoryId())
                .categoryName(categoryDTO.getCategoryName())
                .categoryImageUrl(categoryDTO.getCategoryImageUrl())
                .parentCategory(
                        Category.builder()
                                .categoryId(parentCategoryDto.getCategoryId())
                                .categoryName(parentCategoryDto.getCategoryName())
                                .categoryImageUrl(parentCategoryDto.getCategoryImageUrl())
                                .build())

                .build();


    }
    public static Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryImageUrl(categoryDTO.getCategoryImageUrl());
        // You may handle subcategories and products similarly if needed

        return category;
    }
    public static CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setCategoryImageUrl(category.getCategoryImageUrl());
        // You may handle subcategories and products similarly if needed

        return categoryDTO;
    }


}
