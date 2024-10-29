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
@Table(name = "products", indexes = {
    @Index(name = "idx_code", columnList = "code", unique = true)
})
public class Product {

    @Id
    @JsonProperty(value = "id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JsonProperty(value = "description")
    @Column(name = "description", length = 150)
    String description;

    @JsonProperty(value = "code")
    @Column(name = "code", length = 50, nullable = false, unique = true)
    String code;

    @JsonProperty(value = "stock")
    @Column(name = "stock", nullable = false)
    int stock;

    @JsonProperty(value = "price")
    @Column(name = "price", nullable = false)
    double price;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
