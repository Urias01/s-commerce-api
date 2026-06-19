package com.s.commerce.application.cart.checkOut;

import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.entities.CartItems;
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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        User customer = this.userRepository.findById(request.customerId())
                .orElseThrow(CustomerNotFoundException::new);

        Cart cart = this.cartRepository.findByCustomer(customer)
                .orElseThrow();

        if (request.scheduledDeliveryDate().isBefore(LocalDateTime.now().plusDays(2))) {
            throw new InvalidArgumentException("Delivery must be scheduled at least 2 day in advance");
        }

        LocalDate orderedDate = LocalDate.now();
        Order order = new Order(customer, orderedDate, request.scheduledDeliveryDate());

        if (request.cartItemIdList().isEmpty()) {
            throw new InvalidOperationException("Cart is empty");
        }

        for (CartItemId cartItemId : request.cartItemIdList()) {
            CartItems item = cart.getItems().stream().filter(i -> i.getId().equals(cartItemId)).findFirst().get();

            order.addItem(new OrderItems(item.getQuantity(), item.getPrice(), item.getProduct()));
            cart.removeItem(cartItemId);
        }


        this.orderRepository.create(order);
        this.cartRepository.update(cart);

        return new CheckOutCartResponse(order.getId());
    }
}
