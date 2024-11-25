package com.coderhouse.ecommerce.controller.product;

import com.coderhouse.ecommerce.model.product.ProductSeller;
import com.coderhouse.ecommerce.service.product.ProductSellerService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class ProductSellerController {

  @Autowired
  private ProductSellerService productSellerService;

  @GetMapping
  public ResponseEntity<List<ProductSeller>> listProductSellers() {
    try {
      return ResponseEntity.ok(productSellerService.listProductSellers());
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductSeller> getProductSellerById(@PathVariable String id) {
    try {
      return ResponseEntity.ok(productSellerService.getProductSellerById(id));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping
  public ResponseEntity<ProductSeller> registerProductSeller(@Validated @RequestBody ProductSeller productSeller) {
    try {
      return ResponseEntity.ok(productSellerService.registerProductSeller(productSeller));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

}
