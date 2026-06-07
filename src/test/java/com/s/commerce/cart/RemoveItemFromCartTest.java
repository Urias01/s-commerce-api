package com.s.commerce.cart;

import com.s.commerce.application.cart.removeItem.RemoveItemFromCartRequest;
import com.s.commerce.application.cart.removeItem.RemoveItemFromCartResponse;
import com.s.commerce.application.cart.removeItem.RemoveItemFromCartUseCase;
import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.entities.CartItems;
import com.s.commerce.domain.cart.repositories.ICartRepository;
import com.s.commerce.domain.common.valueObject.Money;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoveItemFromCartTest {

    @InjectMocks
    private RemoveItemFromCartUseCase removeItemFromCartUseCase;

    @Mock
    private ICartRepository cartRepository;
    @Mock
    private IUserRepository userRepository;

    @Test
    @DisplayName("Should be able to remove an item from cart")
    public void shouldBeAbleToRemoveAnItemFromCart() {
        User customer = new User("john doe", new Email("john@doe.com"), new HashedPassword("123"), UserRole.CUSTOMER);
        Product product = new Product("Strawberry Cake", "description", new Money(BigDecimal.valueOf(95)), ProductCategory.CAKE);
        Cart cart = new Cart(customer);
        CartItems item = new CartItems(2, product, cart);
        cart.addItem(item);

        RemoveItemFromCartRequest request = new RemoveItemFromCartRequest(item.getId(), customer.getId());

        when(userRepository.findById(request.customerId())).thenReturn(Optional.of(customer));
        when(cartRepository.findByCustomer(customer)).thenReturn(Optional.of(cart));

        when(cartRepository.update(any(Cart.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        RemoveItemFromCartResponse response = removeItemFromCartUseCase.execute(request);

        verify(userRepository).findById(request.customerId());
        verify(cartRepository).findByCustomer(customer);
        verify(cartRepository).update(argThat(cart1 ->
                cart1.getTotal().equals(Money.ZERO) &&
                        cart1.getItems().isEmpty()
        ));

        assertNotNull(response.id());
    }
}
