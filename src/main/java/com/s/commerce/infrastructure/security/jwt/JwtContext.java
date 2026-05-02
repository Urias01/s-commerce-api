package com.s.commerce.infrastructure.security.jwt;

public class JwtContext {
  private String username;
  private String role;

  public JwtContext(String username, String role) {
    this.username = username;
    this.role = role;
  }

  public String getUsername() {
    return username;
  }

  public String getRole() {
    return role;
  }
}
