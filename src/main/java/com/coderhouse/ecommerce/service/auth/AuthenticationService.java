package com.coderhouse.ecommerce.service.auth;

import com.coderhouse.ecommerce.model.Customer;
import com.coderhouse.ecommerce.model.auth.Session;
import com.coderhouse.ecommerce.model.product.ProductSeller;
import com.coderhouse.ecommerce.service.customer.CustomerService;
import com.coderhouse.ecommerce.service.product.ProductSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  @Autowired
  private ProductSellerService productSellerService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private SessionService sessionService;

  public Session loginSeller(String email, String password) {
    ProductSeller seller = productSellerService.getProductSellerByEmail(email);

    if (!seller.getPassword().equals(password)) {
      return null;
    }

    Session session = sessionService.createSessionPayload("SELLER_");
    session.setOwnerId(seller.getId());

    return sessionService.createSession(session);
  }

  public Session loginCustomer(String email, String password) {
    Customer customer = customerService.getCustomerByEmail(email);

    if (!customer.getPassword().equals(password)) {
      return null;
    }

    Session session = sessionService.createSessionPayload("CUSTOMER_");
    session.setOwnerId(customer.getId());

    return sessionService.createSession(session);
  }

  public ProductSeller getMeSeller(String token) {
    Session session = sessionService.getSession(token.replace("Bearer ", ""));

    if (!sessionService.isSessionValid(session)) {
      throw new IllegalArgumentException("Invalid token");
    }

    return productSellerService.getProductSellerById(session.getOwnerId());
  }

  public Customer getMeCustomer(String token) {
    Session session = sessionService.getSession(token.replace("Bearer ", ""));

    if (!sessionService.isSessionValid(session)) {
      throw new IllegalArgumentException("Invalid token");
    }

    return customerService.getCustomerById(session.getOwnerId());
  }

}
