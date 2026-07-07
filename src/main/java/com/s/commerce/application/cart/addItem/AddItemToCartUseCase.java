package com.s.commerce.application.cart.addItem;

import com.s.commerce.application.cart.mapper.CartItemMapper;
import com.s.commerce.domain.cart.entities.Cart;
import com.s.commerce.domain.cart.entities.CartItems;
import com.s.commerce.domain.cart.repositories.ICartRepository;
import com.s.commerce.domain.products.entity.Product;
import com.s.commerce.domain.products.exceptions.ProductNotFoundException;
import com.s.commerce.domain.products.repositories.IProductRepository;
import com.s.commerce.domain.user.entity.User;
import com.s.commerce.domain.user.exceptions.CustomerNotFoundException;
import com.s.commerce.domain.user.repository.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
        log.info("Starting add item to cart, itemId={}", request.item().productId());
        User customer = this.userRepository.findById(request.customerId())
                .orElseThrow( () -> {
                    log.warn("Customer not found, customerId={}", request.customerId());
                    return new CustomerNotFoundException();
                });

        Cart cart = this.cartRepository.findByCustomer(customer)
                .orElseGet(() -> this.cartRepository.create(new Cart(customer)));

        Product product = this.productRepository.findById(request.item().productId())
                .orElseThrow(() -> {
                    log.warn("Product not found, productId={}", request.item().productId());
                    return new ProductNotFoundException();
                });

        CartItems item = CartItemMapper.toEntity(request.item(), cart, product);

        cart.addItem(item);

        Cart savedCart = cartRepository.update(cart);
        log.info("Item add to cart successful. cartId={}", cart.getId());
        return new AddItemToCartResponse(savedCart.getId());
    }
}
