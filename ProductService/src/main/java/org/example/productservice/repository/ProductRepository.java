package org.example.productservice.repository;

import org.example.productservice.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Integer> {

}
