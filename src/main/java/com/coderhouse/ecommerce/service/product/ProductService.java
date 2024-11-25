package com.coderhouse.ecommerce.service.product;

import com.coderhouse.ecommerce.model.auth.Session;
import com.coderhouse.ecommerce.model.product.Product;
import com.coderhouse.ecommerce.model.product.ProductCategory;
import com.coderhouse.ecommerce.model.product.ProductSeller;
import com.coderhouse.ecommerce.repository.ProductRepository;
import com.coderhouse.ecommerce.service.auth.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductSellerService productSellerService;

  @Autowired
  private SessionService sessionService;

  public Iterable<Product> getProducts() {
    return productRepository.findAll();
  }

    public Product getProduct(String id) {
        return productRepository.findById(id).orElse(null);
    }

  public Product createProduct(Product product, String token) {
    Session session = sessionService.getSession(token.replace("Bearer ", ""));
    if (!sessionService.isSessionValid(session)) {
      throw new IllegalArgumentException("Invalid session");
    }

    ProductSeller seller = productSellerService.getProductSellerById(session.getOwnerId());
    product.setSeller(seller);

    return productRepository.save(product);
  }

  public void deleteProduct(String id, String token) {
    Session session = sessionService.getSession(token.replace("Bearer ", ""));
    if (!sessionService.isSessionValid(session)) {
      throw new IllegalArgumentException("Invalid session");
    }

    Product product = productRepository.findById(id).orElse(null);
    if (product == null) {
      throw new IllegalArgumentException("Product not found");
    }

    if (!product.getSeller().getId().equals(session.getOwnerId())) {
      throw new IllegalArgumentException("Unauthorized");
    }

    productRepository.deleteById(id);
  }

  public Iterable<Product> getProductsByCategory(ProductCategory category) {
    return productRepository.findByCategory(category);
  }

  public Iterable<Product> getProductsByTitle(String title) {
    return productRepository.searchByTitle(title);
  }

}
