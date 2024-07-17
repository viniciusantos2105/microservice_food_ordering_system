package com.viniciusantos2105.orderapi.domain.order.repository;

import com.viniciusantos2105.orderapi.domain.order.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepositoryCustom {

    Order findOrderById(UUID orderId);
    List<Order> findOrdersByRestaurant(UUID restaurantId);
}
