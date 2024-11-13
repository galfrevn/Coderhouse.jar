package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.dto.PurchaseRequestDTO;
import com.coderhouse.ecommerce.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Void> makePurchase(@RequestBody PurchaseRequestDTO purchaseRequest) {
        try {
            purchaseService.processPurchase(purchaseRequest);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
