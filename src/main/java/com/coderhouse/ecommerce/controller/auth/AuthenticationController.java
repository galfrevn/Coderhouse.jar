package com.coderhouse.ecommerce.controller.auth;

import com.coderhouse.ecommerce.model.Customer;
import com.coderhouse.ecommerce.model.auth.Session;
import com.coderhouse.ecommerce.model.product.ProductSeller;
import com.coderhouse.ecommerce.service.auth.AuthenticationService;
import com.coderhouse.ecommerce.service.customer.CustomerService;
import com.coderhouse.ecommerce.service.product.ProductSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  @Autowired
  private ProductSellerService productSellerService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private AuthenticationService authenticationService;

  // # Seller authentication methods

  @PostMapping("/seller/register")
  public ResponseEntity<ProductSeller> registerSeller(@RequestBody ProductSeller seller) {
    return ResponseEntity.ok(productSellerService.registerProductSeller(seller));
  }

  @PostMapping("/seller/login")
  public ResponseEntity<Session> loginSeller(@RequestBody ProductSeller seller) {
    return ResponseEntity.ok(authenticationService.loginSeller(seller.getEmail(), seller.getPassword()));
  }

  // # Customer authentication methods

  @PostMapping("/customer/register")
  public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
    return ResponseEntity.ok(customerService.registerCustomer(customer));
  }

  @PostMapping("/customer/login")
  public ResponseEntity<Session> loginCustomer(@RequestBody ProductSeller customer) {
    return ResponseEntity.ok(authenticationService.loginCustomer(customer.getEmail(), customer.getPassword()));
  }

  // # Customer + Seller authentication methods

  @GetMapping("/me")
  public ResponseEntity<?> getMe(@RequestHeader("Authorization") String token) {
    try {
      if (token.contains("CUSTOMER")) {
        return ResponseEntity.ok(authenticationService.getMeCustomer(token));
      }
      return ResponseEntity.ok(authenticationService.getMeSeller(token));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Invalid token");
    }
  }
}




