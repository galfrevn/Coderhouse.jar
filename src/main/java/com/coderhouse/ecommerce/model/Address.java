package com.coderhouse.ecommerce.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GenerationType;

import jakarta.persistence.ManyToOne;
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
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;


  private String country;
  private String province;
  private String city;

  private String postalCode;

  private String streetName;
  private String streetNumber;

  @ManyToOne(cascade = CascadeType.ALL)
  @JsonIgnore
  private Customer customer;

}
