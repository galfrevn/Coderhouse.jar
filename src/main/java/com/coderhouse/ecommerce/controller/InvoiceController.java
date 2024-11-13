package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.model.Invoice;
import com.coderhouse.ecommerce.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        try {
            return ResponseEntity.ok(invoiceService.getAllInvoices());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(invoiceService.getInvoiceById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        try {
            return ResponseEntity.ok(invoiceService.saveInvoice(invoice));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable String id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
