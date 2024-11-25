package com.coderhouse.ecommerce.service.product;

import com.coderhouse.ecommerce.dto.auth.ProductSellerValidator;
import com.coderhouse.ecommerce.exception.ValidationAdvice;
import com.coderhouse.ecommerce.model.product.ProductSeller;
import com.coderhouse.ecommerce.repository.product.ProductSellerRepository;

import dev.ditsche.validator.error.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class ProductSellerService {

  @Autowired
  private ProductSellerRepository productSellerRepository;

  @Autowired
  private ProductSellerValidator productSellerValidator;

  public ProductSeller registerProductSeller(ProductSeller productSeller) {
    productSellerValidator.validate(productSeller);

    return productSellerRepository.save(productSeller);
  }

  public ProductSeller getProductSellerById(String id) {
    return productSellerRepository.findById(id).orElse(null);
  }

  public ProductSeller getProductSellerByEmail(String email) {
    return productSellerRepository.findByEmail(email).orElse(null);
  }

  public List<ProductSeller> listProductSellers()  {
    return productSellerRepository.findAll();
  }

}
