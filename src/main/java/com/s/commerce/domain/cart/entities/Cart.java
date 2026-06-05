package com.s.commerce.domain.cart.entities;

import com.s.commerce.domain.cart.valueObjects.CartId;
import com.s.commerce.domain.cart.valueObjects.CartItemId;
import com.s.commerce.domain.common.valueObject.Money;
import com.s.commerce.domain.order.entity.OrderItems;
import com.s.commerce.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "carts")
@Getter
public class Cart {

    @EmbeddedId
    private CartId id;

    private Money total;
    @OneToMany
    private Set<CartItems> items = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    public Cart(List<CartItems> items, User customer) {
        this.id = CartId.newId();

        this.total = Money.ZERO;
        this.customer = customer;

        items.forEach(this::addItem);
    }

    public void addItem(CartItems item) {

        this.items.add(item);
        this.total = this.total.add(item.getPrice());
    }

    public void removeItem(CartItems item) {
        this.items.remove(item);
        this.total.subtract(item.getPrice());
    }

}
