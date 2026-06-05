package com.s.commerce.infrastructure.persistence.order.items;

import com.s.commerce.domain.order.repositories.IOrderItemRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemsRepositoryAdapter implements IOrderItemRepository {

    private final OrderItemsRepository orderitemsRepository;

    public OrderItemsRepositoryAdapter(OrderItemsRepository orderitemsRepository) {
        this.orderitemsRepository = orderitemsRepository;
    }
}
