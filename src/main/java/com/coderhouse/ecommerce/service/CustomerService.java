package com.coderhouse.ecommerce.service;


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

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    public List<Invoice> getInvoicesByCustomerId(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return customer.getInvoices();
    }


    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    @Transactional
    public void deleteCustomer(String id) {
        if (!customerRepository.existsById(id)) {
            throw new IllegalArgumentException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

}


