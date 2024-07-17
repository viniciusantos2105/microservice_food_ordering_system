package com.viniciusantos2105.orderapi.domain.order;

import java.util.List;
import java.util.UUID;

public interface OrderRepositoryCustom {

    Order findOrderById(UUID orderId);
    List<Order> findOrdersByRestaurant(UUID restaurantId);
}
