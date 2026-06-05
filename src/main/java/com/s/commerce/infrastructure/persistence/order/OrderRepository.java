package com.s.commerce.infrastructure.persistence.order;

import com.s.commerce.domain.order.entity.Order;
import com.s.commerce.domain.order.valueObject.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
