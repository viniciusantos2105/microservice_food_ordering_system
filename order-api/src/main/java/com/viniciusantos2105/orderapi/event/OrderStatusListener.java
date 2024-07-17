package com.viniciusantos2105.orderapi.event;

import com.viniciusantos2105.orderapi.domain.order.entity.Order;
import com.viniciusantos2105.orderapi.domain.order.entity.OrderHistory;
import com.viniciusantos2105.orderapi.domain.order.repository.OrderHistoryRepository;
import com.viniciusantos2105.orderapi.domain.order.entity.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStatusListener implements EventListener {

    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    public void update(Order order, OrderStatus orderStatus) {
        OrderHistory orderHistory = OrderHistory.create(order, orderStatus);
        orderHistoryRepository.save(orderHistory);
    }
}
