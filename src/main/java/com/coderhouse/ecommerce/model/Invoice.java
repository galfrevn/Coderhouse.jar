package com.coderhouse.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Index;

@Entity
@Table(name = "invoices", indexes = {
    @Index(name = "idx_client_id", columnList = "client_id")
})
public class Invoice {

    @Id
    @JsonProperty(value = "id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // An invoice belongs to a client
    // The client_id column is a foreign key that references the id column in the clients table
    @JsonProperty(value = "clientId")
    @Column(name = "client_id", nullable = false)
    int clientId;

    // DATE TIME DEFAULT CURRENT_TIMESTAMP: The column is automatically set to
    // the current date and time when a new record is inserted
    @JsonProperty(value = "createdAt")
    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    String createdAt;

    @JsonProperty(value = "total")
    @Column(name = "total", nullable = false)
    double total;

    // One Invoice can have many InvoiceDetails
    // The mappedBy attribute is used to specify the property in the InvoiceDetails class that maps the relationship
    // FetchType.LAZY: The invoice details are loaded only when the getInvoiceDetails() method is called
    // CascadeType.ALL: When an invoice is deleted, all its invoice details are also deleted
    @OneToMany(mappedBy = "invoiceId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<InvoiceDetails> invoiceDetails;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<InvoiceDetails> getInvoiceDetails() { return invoiceDetails; }
    public void setInvoiceDetails(List<InvoiceDetails> invoiceDetails) { this.invoiceDetails = invoiceDetails; }
}
