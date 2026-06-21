package com.s.commerce.domain.user.repository;

import java.util.Optional;

import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.valueObject.Email;
import com.s.commerce.domain.user.valueObject.UserId;

public interface IUserRepository {

  Optional<User> findById(UserId userId);
  Optional<User> findByEmail(Email email);
  User create(User user);
  boolean existsByEmail(Email email);

}