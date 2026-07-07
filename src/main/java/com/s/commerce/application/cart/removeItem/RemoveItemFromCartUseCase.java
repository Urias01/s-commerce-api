package com.s.commerce.application.cart.removeItem;

import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.exceptions.CartNotFoundException;
import com.s.commerce.domain.cart.repositories.ICartRepository;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.exceptions.CustomerNotFoundException;
import com.s.commerce.domain.user.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RemoveItemFromCartUseCase {

    private final ICartRepository cartRepository;
    private final IUserRepository userRepository;

    public RemoveItemFromCartUseCase(ICartRepository cartRepository, IUserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public RemoveItemFromCartResponse execute(RemoveItemFromCartRequest request) {
        log.info("Starting processing to remove item from cart, customerId={}", request.customerId());
        User customer = this.userRepository.findById(request.customerId())
                .orElseThrow(() -> {
                    log.warn("Customer not found. customerId={}", request.customerId());
                    return new CustomerNotFoundException();
                });

        Cart cart = this.cartRepository.findByCustomer(customer)
                .orElseThrow(() -> {
                    log.warn("Cart not found. customerId={}", request.customerId());
                    return new CartNotFoundException();
                });

        cart.removeItem(request.itemId());

        this.cartRepository.update(cart);

        log.info("Remove item from cart successful, cartId={}", cart.getId());
        return new RemoveItemFromCartResponse(cart.getId(), cart.getTotal());
    }
}
