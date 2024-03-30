package org.example.productservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@EqualsAndHashCode(callSuper = true, exclude = {"category"})
@Builder
public class Product extends AbstractMappedEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true,nullable = false,updatable = false)
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_image_url")
    private String productImageUrl;

    @Column(name = "sku")
    private String sku;

    @Column(name = "product_price", columnDefinition = "decimal")
    private Double price;

    @Column(name = "product_quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
