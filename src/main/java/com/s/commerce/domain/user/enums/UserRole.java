package com.s.commerce.domain.user.enums;

public enum UserRole {
  ADMIN("Admin"),
  USER("Regular User"),
  GUEST("Guest User");

  private String description;

  UserRole(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}