package com.coderhouse.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Index;

@Entity
@Table(name = "invoice_details", indexes = {
    @Index(name = "idx_invoice_id", columnList = "invoice_id"),
})
public class InvoiceDetails {

    @Id
    @JsonProperty(value = "id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JsonProperty(value = "amount")
    @Column(name = "amount")
    int amount;

    @JsonProperty(value = "price")
    @Column(name = "price")
    double price;

    // An Invoice belongs to an Invoice
    // The invoice_id column is a foreign key that references the id column in the invoices table
    @JsonProperty(value = "invoiceId")
    @Column(name = "invoice_id", nullable = false)
    int invoiceId;

    // An InvoiceDetail belongs to a Product
    // The product_id column is a foreign key that references the id column in the products table
    @JsonProperty(value = "productId")
    @Column(name = "product_id", nullable = false)
    int productId;


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
}
