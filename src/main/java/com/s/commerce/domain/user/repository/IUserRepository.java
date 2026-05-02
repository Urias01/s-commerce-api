package com.s.commerce.domain.user.repository;

import java.util.Optional;
import java.util.UUID;

import com.s.commerce.domain.user.entity.User;

public interface IUserRepository {

  Optional<User> findById(UUID userId);

}