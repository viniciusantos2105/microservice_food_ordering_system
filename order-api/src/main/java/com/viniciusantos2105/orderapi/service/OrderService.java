package com.viniciusantos2105.orderapi.service;

import com.viniciusantos2105.orderapi.domain.order.*;
import com.viniciusantos2105.orderapi.domain.user.User;
import com.viniciusantos2105.orderapi.domain.restaurant.Food;
import com.viniciusantos2105.orderapi.dto.request.FoodsListRequestDto;
import com.viniciusantos2105.orderapi.dto.request.OrderStatusRequestDto;
import com.viniciusantos2105.orderapi.event.OrderStatusListener;
import com.viniciusantos2105.orderapi.exception.unauthorized.UnauthorizedAcessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderStatusListener orderStatusListener;
    private final RestaurantService restaurantService;
    private final OrderRepository orderRepository;
    private final OrderRepositoryImpl orderRepositoryImpl;
    private final OrderHistoryRepositoryImpl orderHistoryRepositoryImpl;

    public Order createOrder(FoodsListRequestDto request, User user) {
        List<Food> foodList = restaurantService.getFoods(request);
        List<OrderFood> foods = foodList.stream()
                .map(OrderFood::create)
                .collect(Collectors.toList()); // Alterado para Collectors.toList()
        Order order = Order.create(user, foods);
        order.addListener(orderStatusListener);
        order.setOrderStatus(order, OrderStatus.PENDING);

        orderRepository.save(order);

        order.getOrderFoods().stream().forEach(orderFood -> {
            orderFood.setOrderId(order.getOrderId());
        });
        orderRepository.save(order);
        return order;
    }

    public Order updateOrderStatus(UUID orderId, OrderStatusRequestDto request, String token){
        Order order = orderRepositoryImpl.findOrderById(orderId);
        restaurantService.isUserRestaurantOwner(token, order.getOrderRestaurant());
        order.addListener(orderStatusListener);

        order.setOrderStatus(order, OrderStatus.fromString(request.getOrderStatus()));
        orderRepository.save(order);
        return order;
    }

    public Order findOrderById(UUID orderId, User user) {
        Order order = orderRepositoryImpl.findOrderById(orderId);
        validateOrderUser(user, order);
        return order;
    }

    public List<Order> findOrdersByRestaurant(UUID restaurantId) {
        return orderRepositoryImpl.findOrdersByRestaurant(restaurantId);
    }

    public List<OrderHistory> findOrderStatusProgress(UUID orderId) {
        return orderHistoryRepositoryImpl.findOrderStatusProgress(orderId);
    }


    private void validateOrderUser(User user, Order order) {
        if (!order.getOrderUser().equals(user.getUserId())) {
            throw UnauthorizedAcessException.create("Usuario não tem acesso a esse pedido", "orderId");
        }
    }
}
