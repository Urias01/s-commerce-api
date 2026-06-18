package com.s.commerce.domain.order.entity;

import com.s.commerce.domain.common.Auditable;
import com.s.commerce.domain.common.valueObject.Money;
import com.s.commerce.domain.order.enums.OrderStatus;
import com.s.commerce.domain.order.valueObject.OrderId;
import com.s.commerce.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
public class Order extends Auditable {

    @EmbeddedId
    private OrderId id;

    private Money total;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDate orderDate;
    private LocalDateTime scheduledDeliveryDate;
    private LocalDateTime deliveredAt;
    @OneToMany
    private Set<OrderItems> items = new HashSet<>();
    @ManyToOne(targetEntity = User.class, optional = false)
    private User customer;

    public Order(User customer, LocalDate orderDate, LocalDateTime scheduledDeliveryDate) {
        super();
        this.id = OrderId.newId();

        this.status = OrderStatus.WAITING_PAYMENT;
        this.items = new HashSet<>();
        this.total = Money.ZERO;
        this.customer = customer;
        this.orderDate = orderDate;
        this.scheduledDeliveryDate = scheduledDeliveryDate;
    }

    protected Order() {
        super();
    }

    public Order(List<OrderItems> items, User customer, LocalDate orderDate, LocalDateTime scheduledDeliveryDate) {
        this.id = OrderId.newId();

        this.status = OrderStatus.PENDING;
        this.items = new HashSet<>();
        this.total = Money.ZERO;
        this.customer = customer;
        this.orderDate = orderDate;
        this.scheduledDeliveryDate = scheduledDeliveryDate;

        items.forEach(this::addItem);
    }

    public void addItem(OrderItems item) {
        if (status.isTerminal())
            throw new IllegalArgumentException("..."); // TODO: Create Domain Exception

        this.items.add(item);
        this.total = this.total.add(item.getPrice());
    }

    public void removeItem(OrderItems item) {
        if (status.isTerminal())
            throw new IllegalArgumentException("..."); // TODO: Create Domain Exception

        this.items.remove(item);
        this.total.subtract(item.getPrice());
    }

    public void changeStatus(OrderStatus next) {
        if (!status.canTransitionTo(next)) {
            throw new IllegalArgumentException("The status cannot be changed to " + next);
        }

        this.status = next;
    }

    public void markAsDelivered() {
        if (this.status != OrderStatus.OUT_FOR_DELIVERY) {
            throw new IllegalStateException("Order is not out for delivery");
        }
        this.status = OrderStatus.DELIVERED;
        this.deliveredAt = LocalDateTime.now();
    }
}
