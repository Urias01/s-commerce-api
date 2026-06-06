package com.s.commerce.infrastructure.persistence.cart;

import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.repositories.ICartRepository;
import com.s.commerce.domain.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CartRepositoryAdapter implements ICartRepository {

    private final CartRepository cartRepository;

    public CartRepositoryAdapter(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> findByCustomer(User customer) {
        return this.cartRepository.findByCustomer(customer);
    }

    @Override
    public Cart create(Cart cart) {
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart update(Cart cart) {
        return this.cartRepository.save(cart);
    }
}
