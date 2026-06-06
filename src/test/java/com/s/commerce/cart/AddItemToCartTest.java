package com.s.commerce.cart;

import com.s.commerce.application.cart.AddItem.AddItemToCartRequest;
import com.s.commerce.application.cart.AddItem.AddItemToCartResponse;
import com.s.commerce.application.cart.AddItem.AddItemToCartUseCase;
import com.s.commerce.application.cart.common.CartItemRequest;
import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.entities.CartItems;
import com.s.commerce.domain.cart.repositories.ICartRepository;
import com.s.commerce.domain.common.valueObject.Money;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.enums.ProductCategory;
import com.s.commerce.domain.products.repositories.IProductRepository;
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
public class AddItemToCartTest {

    @InjectMocks
    private AddItemToCartUseCase addItemToCartUseCase;

    @Mock
    private ICartRepository cartRepository;
    @Mock
    private IUserRepository userRepository;
    @Mock
    private IProductRepository productRepository;

    @Test
    @DisplayName("Should be able to add item to a cart")
    public void shouldBeAbleToAddItemToACart() {
        User customer = new User("john doe", new Email("john@doe.com"), new HashedPassword("123"), UserRole.CUSTOMER);
        Cart cart = new Cart(customer);
        Money price = new Money(BigDecimal.valueOf(95.9));
        Product product = new Product("Chocolate Cake", "A wonderful chocolate cake", price, ProductCategory.CAKE);
        CartItems cartItems = new CartItems(2, product, cart);

        CartItemRequest cartItemRequest = new CartItemRequest(2, product.getId());
        AddItemToCartRequest request = new AddItemToCartRequest(cartItemRequest, customer.getId());

        when(userRepository.findById(request.customerId())).thenReturn(Optional.of(customer));
        when(cartRepository.findByCustomer(customer)).thenReturn(Optional.of(cart));
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        when(cartRepository.update(any(Cart.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AddItemToCartResponse response = addItemToCartUseCase.execute(request);

        verify(userRepository).findById(request.customerId());
        verify(productRepository).findById(product.getId());
        verify(cartRepository).findByCustomer(customer);
        verify(cartRepository).update(argThat(cart1 ->
                cart1.getTotal().equals(cartItems.getPrice())
        ));

        assertNotNull(response.id());
    }

    @Test
    @DisplayName("Should be able to add item and create a cart")
    public void shouldBeAbleToAddItemAndCreateACart() {
        User customer = new User("john doe", new Email("john@doe.com"), new HashedPassword("123"), UserRole.CUSTOMER);
        Cart cart = new Cart(customer);
        Money price = new Money(BigDecimal.valueOf(95.9));
        Product product = new Product("Chocolate Cake", "A wonderful chocolate cake", price, ProductCategory.CAKE);
        CartItems cartItems = new CartItems(2, product, cart);

        CartItemRequest cartItemRequest = new CartItemRequest(2, product.getId());
        AddItemToCartRequest request = new AddItemToCartRequest(cartItemRequest, customer.getId());

        when(userRepository.findById(request.customerId())).thenReturn(Optional.of(customer));
        when(cartRepository.findByCustomer(customer)).thenReturn(Optional.empty());
        when(cartRepository.create(any(Cart.class))).thenReturn(cart);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        when(cartRepository.update(any(Cart.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AddItemToCartResponse response = addItemToCartUseCase.execute(request);

        verify(userRepository).findById(request.customerId());
        verify(productRepository).findById(product.getId());
        verify(cartRepository).findByCustomer(customer);
        verify(cartRepository).update(argThat(cart1 ->
                cart1.getTotal().equals(cartItems.getPrice())
        ));

        assertNotNull(response.id());
    }
}
