package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}