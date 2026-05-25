package com.s.commerce.domain.common.security;

import com.s.commerce.domain.user.valueObject.HashedPassword;

public interface IPasswordHasher {
  String hash(String plainPassword);

  boolean isMatch(String plainPassword, HashedPassword hashedPassword);
}
