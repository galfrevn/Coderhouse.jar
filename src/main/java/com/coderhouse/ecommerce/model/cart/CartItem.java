package com.coderhouse.ecommerce.model.cart;

import com.coderhouse.ecommerce.model.product.Product;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;

import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;

import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import lombok.ToString;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @OneToOne
  @JsonIgnoreProperties(value = {
          "id",
          "seller",
  })
  private Product product;

  private int quantity;
}
