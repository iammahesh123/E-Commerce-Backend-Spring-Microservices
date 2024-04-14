package org.example.proxyclient.bussiness.product.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer categoryId;
    private String categoryTitle;
    private String imageUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CategoryDTO> subCategoriesDtos;

    @JsonProperty("parentCategory")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CategoryDTO parentCategoryDto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ProductDTO> productDtos;

}

