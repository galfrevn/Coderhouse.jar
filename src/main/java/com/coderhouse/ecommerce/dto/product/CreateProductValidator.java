package com.coderhouse.ecommerce.dto.product;

import com.coderhouse.ecommerce.model.product.Product;
import dev.ditsche.validator.Validator;
import org.springframework.stereotype.Service;

import static dev.ditsche.validator.rule.builder.Rules.number;
import static dev.ditsche.validator.rule.builder.Rules.string;

@Service
public class CreateProductValidator {

  public Validator validator = Validator.fromRules(
          string("description").trim().max(255),
          number("price"),
          number("stock").min(1).max(9999),
          string("title").required().trim().max(80)
  );

  public Product validate(Product product) {
    return validator.validate(product);
  }
}
