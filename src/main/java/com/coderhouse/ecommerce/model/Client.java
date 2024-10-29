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
import jakarta.persistence.Index;

@Entity
@Table(name = "clients", indexes = {
    @Index(name = "idx_document", columnList = "document")
})
public class Client {

    @Id
    @JsonProperty(value = "id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JsonProperty(value = "name")
    @Column(name = "name", length = 75, nullable = false)
    String name;

    @JsonProperty(value = "lastName")
    @Column(name = "last_name", length = 75, nullable = false)
    String lastName;

    @JsonProperty(value = "document")
    @Column(name = "document", length = 11, nullable = false, unique = true)
    String document;

    // One User can have many Invoices
    // The mappedBy attribute is used to specify the property in the Invoice class that maps the relationship
    // FetchType.LAZY: The invoices are loaded only when the getInvoices() method is called
    @OneToMany(mappedBy = "clientId", fetch = FetchType.LAZY)
    List<Invoice> invoices;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDocument() { return document; }
    public void setDocument(String document) { this.document = document; }

    public List<Invoice> getInvoices() { return invoices; }
    public void setInvoices(List<Invoice> invoices) { this.invoices = invoices; }
}
