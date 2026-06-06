package com.s.commerce.domain.cart.repositories;

import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.user.entity.User;

import java.util.Optional;

public interface ICartRepository {

    Optional<Cart> findByCustomer(User customer);
    Cart create(Cart cart);
    Cart update(Cart cart);

}
