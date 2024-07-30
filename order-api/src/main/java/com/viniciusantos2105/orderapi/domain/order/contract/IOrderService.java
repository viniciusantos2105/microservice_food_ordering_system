package com.viniciusantos2105.orderapi.domain.order.contract;

import com.viniciusantos2105.orderapi.domain.order.entity.Order;
import com.viniciusantos2105.orderapi.domain.order.entity.OrderHistory;
import com.viniciusantos2105.orderapi.domain.user.entity.User;
import com.viniciusantos2105.orderapi.dto.request.FoodsListRequestDto;
import com.viniciusantos2105.orderapi.dto.request.OrderStatusRequestDto;

import java.util.List;
import java.util.UUID;

public interface IOrderService {

    Order createOrder(FoodsListRequestDto request, User user);
    Order updateOrderStatus(UUID orderId, OrderStatusRequestDto request, String token);
    Order findOrderById(UUID orderId, User user);
    List<Order> findOrdersByRestaurant(UUID restaurantId);
    List<OrderHistory> findOrderStatusProgress(UUID orderId);
}
