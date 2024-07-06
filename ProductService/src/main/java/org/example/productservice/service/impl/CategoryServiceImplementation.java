package org.example.productservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.productservice.domain.entity.Category;
import org.example.productservice.domain.dto.CategoryDTO;
import org.example.productservice.exception.CategoryNotFoundException;
import org.example.productservice.mapper.CategoryMapping;
import org.example.productservice.repository.CategoryRepository;
import org.example.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.productservice.mapper.CategoryMapping.convertToDTO;
import static org.example.productservice.mapper.CategoryMapping.convertToEntity;

@Service
@Transactional
@Slf4j

public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        log.info("CategoryDTO, Fetch all the categories");
        return this.categoryRepository.findAll()
                .stream()
                .map(CategoryMapping::map)
                .distinct().collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Integer categoryId) {
        log.info("CategoryDTO, Fetch the categories by using categoryId");
        return this.categoryRepository.findById(categoryId)
                .map(CategoryMapping::map).
                orElseThrow(()->new CategoryNotFoundException(String.format("Category with id %d is not found",categoryId)));

    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        log.info("Saving the category");

        // Convert DTO to Entity
        Category category = convertToEntity(categoryDTO);

        // If the parent category is provided in the DTO, find it in the database
        if (categoryDTO.getParentCategoryDTO() != null && categoryDTO.getParentCategoryDTO().getCategoryId() != null) {
            Category parentCategory = categoryRepository.findById(categoryDTO.getParentCategoryDTO().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParentCategory(parentCategory);
        }

        // Save the category entity
        Category savedCategory = this.categoryRepository.save(category);

        // Convert the saved entity back to DTO and return
        return convertToDTO(savedCategory);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        log.info("CategoryDTO, update the category");
        return CategoryMapping.map(this.categoryRepository.save(CategoryMapping.map(categoryDTO)));
    }

    @Override
    public CategoryDTO update(Integer categoryId, CategoryDTO categoryDTO) {
        log.info("CategoryDTO, update the category by using categoryId");
        return CategoryMapping.map(this.categoryRepository.save(CategoryMapping.map(this.findById(categoryId))));
    }

    @Override
    public void deleteById(Integer categoryId) {
        log.info("CategoryDTO, delete the category");
        this.categoryRepository.deleteById(categoryId);


    }
}
