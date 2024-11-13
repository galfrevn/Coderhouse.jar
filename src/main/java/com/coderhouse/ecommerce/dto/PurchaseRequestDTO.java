package com.coderhouse.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PurchaseRequestDTO {

    private String customerId;
    private List<String> productIds;

}