package com.coderhouse.ecommerce.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.ecommerce.model.product.ProductSeller;

import java.util.Optional;

public interface ProductSellerRepository extends JpaRepository<ProductSeller, String> {

    Optional<ProductSeller> findByEmail(String email);
}