package com.s.commerce.cart;

import com.s.commerce.application.cart.checkOut.CheckOutCartRequest;
import com.s.commerce.application.cart.checkOut.CheckOutCartResponse;
import com.s.commerce.application.cart.checkOut.CheckOutCartUseCase;
import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.entities.CartItems;
import com.s.commerce.domain.cart.repositories.ICartRepository;
import com.s.commerce.domain.common.valueObject.Money;
import com.s.commerce.domain.order.entity.Order;
import com.s.commerce.domain.order.repositories.IOrderRepository;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.enums.UserRole;
import com.s.commerce.domain.user.repository.IUserRepository;
import com.s.commerce.domain.user.valueObject.Email;
import com.s.commerce.domain.user.valueObject.HashedPassword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckoutCartTest {

    @InjectMocks
    private CheckOutCartUseCase checkOutCartUseCase;

    @Mock
    private ICartRepository cartRepository;

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private IUserRepository userRepository;

    @Test
    @DisplayName("Should be able to check out cart")
    public void shouldBeAbleToCheckOutCart() {
        User customer = new User("john doe", new Email("john@mail.com"), new HashedPassword("39ufjaosa"), UserRole.CUSTOMER);
        Cart cart = new Cart(customer);
        Product strawberryCake = new Product("Strawberry Cake", "description", new Money(BigDecimal.valueOf(95.9)), ProductCategory.CAKE);
        CartItems item = new CartItems(1, strawberryCake, cart);
        cart.addItem(item);

        CheckOutCartRequest request = new CheckOutCartRequest(List.of(item.getId()), LocalDateTime.now().plusDays(3), customer.getId());

        when(this.userRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(this.cartRepository.findByCustomer(customer)).thenReturn(Optional.of(cart));

        when(cartRepository.update(any(Cart.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CheckOutCartResponse response = checkOutCartUseCase.execute(request);

        verify(userRepository).findById(request.customerId());
        verify(orderRepository).create(any(Order.class));
        verify(cartRepository).findByCustomer(customer);
        verify(cartRepository).update(argThat(cart1 ->
                cart1.getItems().isEmpty()
        ));

        assertNotNull(response.id());

    }
}
