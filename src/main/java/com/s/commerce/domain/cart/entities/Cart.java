package com.s.commerce.domain.cart.entities;

import com.s.commerce.domain.cart.exceptions.CartItemNotFoundException;
import com.s.commerce.domain.cart.valueObjects.CartId;
import com.s.commerce.domain.cart.valueObjects.CartItemId;
import com.s.commerce.domain.common.Auditable;
import com.s.commerce.domain.common.valueObject.Money;
import com.s.commerce.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Getter
public class Cart extends Auditable {

    @EmbeddedId
    private CartId id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItems> items = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    protected Cart() {
        super();
    }

    public Cart(User customer) {
        super();
        this.id = CartId.newId();

        this.customer = customer;
    }

    public void addItem(CartItems item) {
        this.items.stream()
                .filter(existing -> existing.isSameProduct(item))
                .findFirst()
                .ifPresentOrElse(
                        existing -> existing.increaseQuantity(item.getQuantity()),
                        () -> this.items.add(item)
                );
    }

    public void removeItem(CartItemId itemId) {
        CartItems item = this.items.stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(CartItemNotFoundException::new);

        this.items.remove(item);
    }

    public Money getTotal() {
        return this.items.stream()
                .map(CartItems::getPrice)
                .reduce(Money.ZERO, Money::add);
    }

}
