package com.s.commerce.infrastructure.persistence.user;

import java.util.Optional;

import com.s.commerce.domain.user.valueObject.Email;
import com.s.commerce.domain.user.valueObject.UserId;
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
    public Optional<User> findById(UserId id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

}