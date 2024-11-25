package com.coderhouse.ecommerce.model.cart;

import com.coderhouse.ecommerce.model.Customer;
import com.coderhouse.ecommerce.model.cart.CartItem;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GenerationType;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;

import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @OneToMany(cascade = CascadeType.ALL)
  private List<CartItem> items = new ArrayList<>();

  private double total;

  @OneToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  private Customer customer;

}
