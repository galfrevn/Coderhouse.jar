package com.coderhouse.ecommerce.model.product;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductSeller {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;
  private String lastName;
  private String document;

  private String email;
  private String phone;

  private String password;

  @OneToMany(cascade = CascadeType.REMOVE)
  @JsonIgnore
  private List<Product> products;
}
