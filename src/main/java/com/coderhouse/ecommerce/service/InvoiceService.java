package com.coderhouse.ecommerce.service;

import com.coderhouse.ecommerce.model.Invoice;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.coderhouse.ecommerce.repository.InvoiceRepository;
import com.coderhouse.ecommerce.repository.CustomerRepository;


import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(String id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invoice not found"));
    }

    public Invoice saveInvoice(Invoice invoice) {
        if (!customerRepository.existsById(invoice.getCustomer().getId())) {
            throw new IllegalArgumentException("Customer not found");
        }


        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(String id) {
        if (!invoiceRepository.existsById(id)) {
            throw new IllegalArgumentException("Invoice not found");
        }
        invoiceRepository.deleteById(id);
    }


}
