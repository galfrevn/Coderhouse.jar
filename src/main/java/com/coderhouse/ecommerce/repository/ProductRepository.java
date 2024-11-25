package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.product.Product;
import com.coderhouse.ecommerce.model.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByCategory(ProductCategory category);

    @Query("SELECT p FROM Product p WHERE p.title LIKE %:query%")
    List<Product> searchByTitle(String query);

}