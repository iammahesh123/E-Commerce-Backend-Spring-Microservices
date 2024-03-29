package org.example.productservice.service;

import org.example.productservice.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAll();
    CategoryDTO findById(final Integer categoryId);
    CategoryDTO save(final CategoryDTO categoryDTO);
    CategoryDTO update(final CategoryDTO categoryDTO);
    CategoryDTO update(final Integer categoryId,final CategoryDTO categoryDTO);
    void deleteById(final Integer categoryId);
}
