package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByCategoryId(String categoryId);
    List<Product> findByTitleContainingIgnoreCase(String query);

}