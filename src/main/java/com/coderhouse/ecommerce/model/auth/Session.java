package com.coderhouse.ecommerce.model.auth;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import org.hibernate.annotations.UuidGenerator;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Session {

  @Id
  @UuidGenerator
  private String id;

  private String token;
  private String ownerId;

  private LocalDateTime createdAt;
  private LocalDateTime expiresAt;

}
