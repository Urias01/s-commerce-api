package com.s.commerce.domain.common.security;

public interface IPasswordHasher {
  String hash(String plainPassword);

  boolean isMatch(String plainPassword, String hashedPassword);
}
