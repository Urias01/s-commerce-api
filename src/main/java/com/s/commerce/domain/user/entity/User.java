package com.s.commerce.domain.user.entity;

import java.util.UUID;

import com.s.commerce.domain.common.Auditable;
import com.s.commerce.domain.user.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private UserRole role;

  protected User() {
  }

  public User(UserRole role) {
    this.role = role;
  }

  public UUID getId() {
    return id;
  }

  public UserRole getRole() {
    return role;
  }
}