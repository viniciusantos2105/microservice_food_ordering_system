package com.viniciusantos2105.orderapi.event;

import com.viniciusantos2105.orderapi.domain.order.Order;
import com.viniciusantos2105.orderapi.domain.order.OrderHistory;
import com.viniciusantos2105.orderapi.domain.order.OrderHistoryRepository;
import com.viniciusantos2105.orderapi.domain.order.OrderStatus;
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
