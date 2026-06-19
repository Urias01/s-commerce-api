package com.s.commerce.domain.cart.entities;

import com.s.commerce.domain.cart.valueObjects.CartItemId;
import com.s.commerce.domain.common.exceptions.InvalidOperationException;
import com.s.commerce.domain.common.valueObject.Money;
import com.s.commerce.domain.products.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "cart_items")
@Getter
public class CartItems {

    @EmbeddedId
    private CartItemId id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    protected CartItems() {
    }

    public CartItems(int quantity, Product product, Cart cart) {
        this.id = CartItemId.newId();

        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
    }

    public void plusQuantity() {
        this.quantity++;
    }

    public void minusQuantity() {
        if (this.quantity <= 1) {
            throw new InvalidOperationException("Sugerir remover produto do carrinho");
        }
        this.quantity--;
    }

    public boolean isSameProduct(CartItems other) {
        return this.product.getId().equals(other.getProduct().getId());
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public Money getPrice() {
        return this.product.getPrice().multiply(this.quantity);
    }
}
