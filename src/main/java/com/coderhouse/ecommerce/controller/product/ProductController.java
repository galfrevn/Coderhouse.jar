package com.coderhouse.ecommerce.controller.product;

import com.coderhouse.ecommerce.dto.product.CreateProductValidator;
import com.coderhouse.ecommerce.model.product.Product;
import com.coderhouse.ecommerce.model.product.ProductCategory;
import com.coderhouse.ecommerce.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CreateProductValidator createProductValidator;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Iterable<Product>> getProductsByCategory(@PathVariable ProductCategory category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> searchProducts(@RequestParam String query) {
        return ResponseEntity.ok(productService.getProductsByTitle(query));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestHeader("Authorization") String token, @RequestBody Product product) {
        try {
            createProductValidator.validate(product);
            return ResponseEntity.ok(productService.createProduct(product, token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@RequestHeader("Authorization") String token, @PathVariable String id) {
        productService.deleteProduct(id, token);
        return ResponseEntity.noContent().build();
    }

}
