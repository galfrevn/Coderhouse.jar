package com.coderhouse.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
  private String productId;
  private Integer quantity;
}
