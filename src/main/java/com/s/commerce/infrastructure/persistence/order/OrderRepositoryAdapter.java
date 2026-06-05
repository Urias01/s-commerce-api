package com.s.commerce.infrastructure.persistence.order;

import com.s.commerce.domain.order.repositories.IOrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements IOrderRepository {

    private final OrderRepository orderRepository;

    public OrderRepositoryAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
