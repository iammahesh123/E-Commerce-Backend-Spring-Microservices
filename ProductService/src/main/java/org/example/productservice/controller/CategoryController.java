package org.example.productservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.productservice.dto.CategoryDTO;
import org.example.productservice.response.DTOCollectionResponse;
import org.example.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> save(
            @RequestBody
            @NotNull(message = "Input must be not null")
            @Valid CategoryDTO categoryDTO)
    {
        return ResponseEntity.ok(this.categoryService.save(categoryDTO));
    }

    @GetMapping
    public ResponseEntity<DTOCollectionResponse<CategoryDTO>> findAll() {
        return ResponseEntity.ok(new DTOCollectionResponse<>(this.categoryService.findAll()));
    }

    @GetMapping("/categoryId")
    public ResponseEntity<CategoryDTO> findById(
            @PathVariable("categoryId")
            @RequestBody
            @NotNull(message = "Input must be not NULL")
            @Valid final String categoryId)
    {
        return ResponseEntity.ok(this.categoryService.findById(Integer.parseInt(categoryId)));
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(
            @RequestBody
            @NotNull(message = "Input must be not NULL")
            @Valid CategoryDTO categoryDTO) {
        return ResponseEntity.ok(this.categoryService.update(categoryDTO));
    }

    @PutMapping("/categoryId")
    public ResponseEntity<CategoryDTO> update(
            @PathVariable("categoryId")
            @RequestBody
            @NotNull(message = "Input must be not null")
            @Valid String categoryId,
            @RequestBody
            @NotNull(message = "Input must be not null")
            @Valid CategoryDTO categoryDTO)
    {
        return ResponseEntity.ok(this.categoryService.update(Integer.parseInt(categoryId),categoryDTO));
    }

    @DeleteMapping("/categoryId")
    public ResponseEntity<Boolean> delete(
            @PathVariable("categoryId")
            @RequestBody
            @NotNull(message = "Input must be not NULL")
            @Valid String categoryId,
            @RequestBody
            @NotNull(message = "Input must be not NULL")
            @Valid CategoryDTO categoryDTO)
    {
        this.categoryService.deleteById(Integer.parseInt(categoryId));
        return ResponseEntity.ok(true);
    }
}
