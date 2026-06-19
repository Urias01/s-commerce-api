package com.s.commerce.domain.order.entity;

import com.s.commerce.domain.common.Auditable;
import com.s.commerce.domain.common.exceptions.InvalidOperationException;
import com.s.commerce.domain.common.valueObject.Money;
import com.s.commerce.domain.order.valueObject.OrderItemId;
import com.s.commerce.domain.products.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "order_items")
@Getter
public class OrderItems extends Auditable {

    @EmbeddedId
    private OrderItemId id;

    private int quantity;

    private Money price;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public OrderItems(int quantity, Money price, Product product) {
        this.id = OrderItemId.newId();

        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public void addItem() {
        this.quantity++;
    }

    public void removeItem() {
        if (this.quantity <= 0) {
            throw new InvalidOperationException("Is not possible remove an item");
        }

        this.quantity--;
    }

    public void definePrice(Money value) {
        this.price = value;
    }
}
