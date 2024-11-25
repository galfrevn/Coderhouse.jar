package com.coderhouse.ecommerce.model.payment;

import com.coderhouse.ecommerce.model.Customer;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GenerationType;

import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;

import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import lombok.ToString;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class PaymentMethod {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String number;
  private String cvv;

  private String expirationDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  private Customer customer;

}
