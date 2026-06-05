package com.s.commerce.infrastructure.persistence.cart;

import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.valueObjects.CartId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, CartId> {
}
