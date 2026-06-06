package com.s.commerce.infrastructure.persistence.cart;

import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.valueObjects.CartId;
import com.s.commerce.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, CartId> {

    Optional<Cart> findByCustomer(User customer);
}
