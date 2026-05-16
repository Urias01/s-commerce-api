package com.s.commerce.infrastructure.persistence.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.repository.IUserRepository;

@Repository
public class UserRepositoryAdapter implements IUserRepository {

  private final UserRepository userRepository;

  public UserRepositoryAdapter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Optional<User> findById(UUID id) {
    return userRepository.findById(id);
  }

}