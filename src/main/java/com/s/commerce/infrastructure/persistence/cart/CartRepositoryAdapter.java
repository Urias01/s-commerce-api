package com.s.commerce.infrastructure.persistence.cart;

import com.s.commerce.domain.cart.repositories.ICartRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepositoryAdapter implements ICartRepository {

    private final CartRepository cartRepository;

    public CartRepositoryAdapter(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
}
