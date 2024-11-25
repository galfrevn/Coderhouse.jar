package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.dto.CartDTO;
import com.coderhouse.ecommerce.service.customer.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

  @Autowired
  private CartService cartService;

  @PostMapping("/add")
  public ResponseEntity<?> addProductToCart(@RequestHeader("Authorization") String token, @RequestBody CartDTO cart) {
    try {
      return ResponseEntity.ok(cartService.addProductToCart(token, cart.getProductId(), cart.getQuantity()));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @DeleteMapping("/remove")
  public ResponseEntity<?> removeProductFromCart(@RequestHeader("Authorization") String token, @RequestBody CartDTO cart) {
    try {
      return ResponseEntity.ok(cartService.removeProductFromCart(token, cart.getProductId()));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping("/checkout")
  public ResponseEntity<?> checkout(@RequestHeader("Authorization") String token) {
    try {
      return ResponseEntity.ok(cartService.checkout(token));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping
  public ResponseEntity<?> getCart(@RequestHeader("Authorization") String token) {
    try {
      return ResponseEntity.ok(cartService.getCartByCustomer(token));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }
}
