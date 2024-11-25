package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.model.Customer;
import com.coderhouse.ecommerce.model.Invoice;
import com.coderhouse.ecommerce.service.customer.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(customerService.getCustomerById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}/invoices")
    public ResponseEntity<List<Invoice>> getInvoicesByCustomerId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(customerService.getInvoicesByCustomerId(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
