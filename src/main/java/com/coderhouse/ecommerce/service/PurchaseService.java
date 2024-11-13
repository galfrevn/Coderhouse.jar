package com.coderhouse.ecommerce.service;

import com.coderhouse.ecommerce.dto.PurchaseRequestDTO;
import com.coderhouse.ecommerce.model.Customer;
import com.coderhouse.ecommerce.model.Invoice;
import com.coderhouse.ecommerce.model.Product;
import com.coderhouse.ecommerce.repository.CustomerRepository;
import com.coderhouse.ecommerce.repository.InvoiceRepository;
import com.coderhouse.ecommerce.repository.ProductRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Transactional
    public void processPurchase(PurchaseRequestDTO purchaseRequest) {
        Customer customer = customerRepository.findById(purchaseRequest.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        List<Product> products = productRepository.findAllById(purchaseRequest.getProductIds());
        if (products.size() != purchaseRequest.getProductIds().size()) {
            throw new IllegalArgumentException("Some products not found");
        }

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setProducts(products);

        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        invoice.setTotal(total);

        for (Product product : products) {
            if (product.getStock() > 0) {
                product.setStock(product.getStock() - 1);
                productRepository.save(product);
            } else {
                throw new IllegalArgumentException("Product " + product.getTitle() + " is out of stock");
            }
        }

        invoiceRepository.save(invoice);
    }
}
