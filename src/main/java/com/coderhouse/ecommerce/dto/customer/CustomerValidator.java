package com.coderhouse.ecommerce.dto.customer;

import com.coderhouse.ecommerce.model.Customer;
import dev.ditsche.validator.Validator;
import org.springframework.stereotype.Service;

import static dev.ditsche.validator.rule.builder.Rules.*;

@Service
public class CustomerValidator {

  public Validator validator = Validator.fromRules(
          string("name").required().trim().max(50),
          string("lastName").required().trim().max(50),
          string("email").required().trim().email().max(50),
          string("document").required().trim().alphanum().max(11),
          string("password").required().trim().min(8).max(50)
  );

  public void validate(Customer customer) {
    validator.validate(customer);
  }

}