package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}