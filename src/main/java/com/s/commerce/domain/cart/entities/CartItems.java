package com.s.commerce.domain.cart.entities;

import com.s.commerce.domain.cart.valueObjects.CartItemId;
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

    private Money price;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public CartItems(Money price, int quantity, Product product) {
        this.id = CartItemId.newId();

        this.price = price;
        this.quantity = quantity;
        this.product = product;
    }

    public void plusQuantity() {
        this.quantity++;
    }

    public void minusQuantity() {
        if(this.quantity <= 1) {
            throw new IllegalArgumentException("Sugerir remover produto do carrinho");
        }
        this.quantity--;
    }

}
