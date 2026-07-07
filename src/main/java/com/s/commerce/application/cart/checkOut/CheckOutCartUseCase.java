package com.s.commerce.application.cart.checkOut;

import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.entities.CartItems;
import com.s.commerce.domain.cart.exceptions.CartNotFoundException;
import com.s.commerce.domain.cart.exceptions.CartItemNotFoundException;
import com.s.commerce.domain.cart.repositories.ICartRepository;
import com.s.commerce.domain.cart.valueObjects.CartItemId;
import com.s.commerce.domain.common.exceptions.InvalidArgumentException;
import com.s.commerce.domain.common.exceptions.InvalidOperationException;
import com.s.commerce.domain.order.entity.Order;
import com.s.commerce.domain.order.entity.OrderItems;
import com.s.commerce.domain.order.repositories.IOrderRepository;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.exceptions.CustomerNotFoundException;
import com.s.commerce.domain.user.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
public class CheckOutCartUseCase {

    private final ICartRepository cartRepository;
    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;

    public CheckOutCartUseCase(ICartRepository cartRepository, IOrderRepository orderRepository, IUserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CheckOutCartResponse execute(CheckOutCartRequest request) {
        log.info("Starting checkout processing. customerId={}, scheduledDeliveryDate={}, items={}",
                request.customerId(), request.scheduledDeliveryDate(), request.cartItemIdList().size());
        User customer = this.userRepository.findById(request.customerId())
                .orElseThrow(() -> {
                    log.warn("Customer not found, customerId={}", request.customerId());
                    return new CustomerNotFoundException();
                });

        Cart cart = this.cartRepository.findByCustomer(customer)
                .orElseThrow(() -> {
                    log.warn("Customer's cart is not found, customerId={}", request.customerId());
                    return new CartNotFoundException();
                });

        if (request.scheduledDeliveryDate().isBefore(LocalDateTime.now().plusDays(2))) {
            log.warn("Delivery must be scheduled at least 2 day in advance");
            throw new InvalidArgumentException("Delivery must be scheduled at least 2 day in advance");
        }

        LocalDate orderedDate = LocalDate.now();
        Order order = new Order(customer, orderedDate, request.scheduledDeliveryDate());

        if (request.cartItemIdList().isEmpty()) {
            log.warn("Cart is empty");
            throw new InvalidOperationException("Cart is empty");
        }

        log.info(
                "Persisting order. customerId={}, totalItems={}",
                customer.getId(),
                order.getItems().size()
        );

        for (CartItemId cartItemId : request.cartItemIdList()) {
            CartItems item = cart.getItems().stream()
                    .filter(i -> i.getId().equals(cartItemId))
                    .findFirst()
                    .orElseThrow(() -> {
                        log.warn("Cart item not found");
                        return new CartItemNotFoundException();
                    });

            order.addItem(new OrderItems(item.getQuantity(), item.getPrice(), item.getProduct()));
            cart.removeItem(cartItemId);
        }


        this.orderRepository.create(order);
        this.cartRepository.update(cart);

        log.info("Checkout was successful, orderId={}", order.getId());
        return new CheckOutCartResponse(order.getId());
    }
}
