package com.s.commerce.domain.user.enums;

import lombok.Getter;

@Getter
public enum UserRole {
  ADMIN("Admin"),
  CUSTOMER("Customer");

  private final String description;

  UserRole(String description) {
    this.description = description;
  }

}