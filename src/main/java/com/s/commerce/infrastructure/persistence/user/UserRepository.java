package com.s.commerce.infrastructure.persistence.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.s.commerce.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
