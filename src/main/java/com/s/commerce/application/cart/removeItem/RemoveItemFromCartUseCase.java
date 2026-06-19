package com.s.commerce.application.cart.removeItem;

import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.exceptions.CartNotFoundException;
import com.s.commerce.domain.cart.repositories.ICartRepository;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.exceptions.CustomerNotFoundException;
import com.s.commerce.domain.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoveItemFromCartUseCase {

    private final ICartRepository cartRepository;
    private final IUserRepository userRepository;

    public RemoveItemFromCartUseCase(ICartRepository cartRepository, IUserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request) {
        User customer = this.userRepository.findById(request.customerId())
                .orElseThrow(CustomerNotFoundException::new);

        Cart cart = this.cartRepository.findByCustomer(customer)
                .orElseThrow(CartNotFoundException::new);

        cart.removeItem(request.itemId());

        this.cartRepository.update(cart);

        return new RemoveItemFromCartResponse(cart.getId(), cart.getTotal());
    }
}
