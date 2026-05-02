package com.s.commerce.infrastructure.security.hashing;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.s.commerce.domain.common.security.IPasswordHasher;

public class BCryptPasswordHasher implements IPasswordHasher {

  private final PasswordEncoder encoder;

  public BCryptPasswordHasher(PasswordEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public String hash(String rawPassword) {
    return encoder.encode(rawPassword);
  }

  @Override
  public boolean isMatch(String rawPassword, String hashedPassword) {
    return encoder.matches(rawPassword, hashedPassword);
  }
}