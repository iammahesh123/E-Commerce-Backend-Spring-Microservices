package org.example.productservice.implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.productservice.dto.CategoryDTO;
import org.example.productservice.exception.CategoryNotFoundException;
import org.example.productservice.helper.CategoryMapping;
import org.example.productservice.repository.CategoryRepository;
import org.example.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
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

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        log.info("CategoryDTO, save the category");
        return CategoryMapping.map(this.categoryRepository.save(CategoryMapping.map(categoryDTO)));
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
