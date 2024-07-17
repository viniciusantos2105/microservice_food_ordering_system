package com.viniciusantos2105.orderapi.event;

import com.viniciusantos2105.orderapi.domain.order.entity.Order;
import com.viniciusantos2105.orderapi.domain.order.entity.OrderStatus;

public interface EventListener {
    void update(Order order, OrderStatus orderStatus);
}
