package com.coderhouse.ecommerce.service.customer;


import com.coderhouse.ecommerce.dto.customer.CustomerValidator;
import com.coderhouse.ecommerce.model.Customer;
import com.coderhouse.ecommerce.model.Invoice;
import com.coderhouse.ecommerce.repository.CustomerRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerValidator customerValidator;

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

  public Customer getCustomerById(String id) {
    return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
  }

  public List<Invoice> getInvoicesByCustomerId(String id) {
    Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    return customer.getInvoices();
  }


  public void deleteCustomer(String id) {
    if (!customerRepository.existsById(id)) {
      throw new IllegalArgumentException("Customer not found");
    }
    customerRepository.deleteById(id);
  }

  public Customer registerCustomer(Customer customer) {
      customerValidator.validate(customer);
      if (customerRepository.existsById(customer.getDocument())) {
        throw new IllegalArgumentException("Customer already exists");
      }

      return customerRepository.save(customer);
  }

}


