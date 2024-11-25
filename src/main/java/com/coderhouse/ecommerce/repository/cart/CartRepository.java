package com.coderhouse.ecommerce.repository.cart;

import com.coderhouse.ecommerce.model.cart.Cart;
import com.coderhouse.ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {
  Optional<Cart> findByCustomer(Customer customer);
}