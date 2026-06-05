package com.s.commerce.infrastructure.persistence.order.items;

import com.s.commerce.domain.order.entity.OrderItems;
import com.s.commerce.domain.order.valueObject.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, OrderItemId> {
}
