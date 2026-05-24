package com.s.commerce.infrastructure.persistence.user;

import java.util.Optional;

import com.s.commerce.domain.user.valueObject.Email;
import com.s.commerce.domain.user.valueObject.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import com.s.commerce.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, UserId> {

    Optional<User> findByEmail(Email email);

}
