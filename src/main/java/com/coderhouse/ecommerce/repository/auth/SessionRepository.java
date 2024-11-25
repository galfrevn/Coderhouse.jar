package com.coderhouse.ecommerce.repository.auth;

import com.coderhouse.ecommerce.model.auth.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {
  Session findByToken(String token);
}