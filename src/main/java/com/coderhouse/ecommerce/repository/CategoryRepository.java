package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}