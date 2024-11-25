package com.coderhouse.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String lastName;
    private String document;

    private String email;
    private String password;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Invoice> invoices = List.of();
}
