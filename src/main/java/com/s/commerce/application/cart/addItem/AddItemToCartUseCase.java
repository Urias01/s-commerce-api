package com.s.commerce.application.cart.addItem;

import com.s.commerce.application.cart.mapper.CartItemMapper;
import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.entities.CartItems;
import com.s.commerce.domain.cart.repositories.ICartRepository;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.repositories.IProductRepository;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AddItemToCartUseCase {

    private final ICartRepository cartRepository;
    private final IUserRepository userRepository;
    private final IProductRepository productRepository;

    public AddItemToCartUseCase(ICartRepository cartRepository, IUserRepository userRepository, IProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public AddItemToCartResponse execute(AddItemToCartRequest request) {

        User customer = this.userRepository.findById(request.customerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Cart cart = this.cartRepository.findByCustomer(customer)
                .orElseGet(() -> this.cartRepository.create(new Cart(customer)));

        Product product = this.productRepository.findById(request.item().productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        CartItems item = CartItemMapper.toEntity(request.item(), cart, product);

        cart.addItem(item);

        Cart savedCart = cartRepository.update(cart);
        return new AddItemToCartResponse(savedCart.getId());
    }
}
