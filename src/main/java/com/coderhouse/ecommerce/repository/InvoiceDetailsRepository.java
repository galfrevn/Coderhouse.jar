package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Integer> {
}