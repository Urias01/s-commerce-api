package com.s.commerce.domain.order.repositories;

import com.s.commerce.domain.order.entity.Order;

public interface IOrderRepository {

    Order create(Order order);
}
