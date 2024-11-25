package com.coderhouse.ecommerce.service.auth;

import com.coderhouse.ecommerce.model.auth.Session;
import com.coderhouse.ecommerce.repository.auth.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessionService {

  @Autowired
  private SessionRepository sessionRepository;


  public Session createSession(Session session) {
    return sessionRepository.save(session);
  }

  public Session createSessionPayload(String tokenPrefix) {
    Session session = new Session();

    session.setToken(tokenPrefix + UUID.randomUUID());
    session.setCreatedAt(LocalDateTime.now());
    session.setExpiresAt(LocalDateTime.now().plusHours(1));

    return session;
  }

  public Session getSession(String token) {
    return sessionRepository.findByToken(token);
  }

  public boolean isSessionValid(Session session) {
    return session.getExpiresAt().isAfter(LocalDateTime.now());
  }

}
