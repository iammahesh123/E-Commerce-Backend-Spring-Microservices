package org.example.productservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer categoryId;
    private String categoryName;
    private String categoryImageUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<CategoryDTO> subCategoriesDTO;

    @JsonProperty("parentCategory")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CategoryDTO parentCategoryDTO;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<ProductDTO> productDTOS;

}
