package com.viniciusantos2105.orderapi.service;

import com.viniciusantos2105.orderapi.adapter.Adapter;
import com.viniciusantos2105.orderapi.domain.order.Order;
import com.viniciusantos2105.orderapi.domain.order.OrderFood;
import com.viniciusantos2105.orderapi.domain.order.OrderRepository;
import com.viniciusantos2105.orderapi.domain.order.OrderRepositoryImpl;
import com.viniciusantos2105.orderapi.domain.user.User;
import com.viniciusantos2105.orderapi.domain.restaurant.Food;
import com.viniciusantos2105.orderapi.dto.FoodsListRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestaurantService restaurantService;
    private final OrderRepository orderRepository;
    private final OrderRepositoryImpl orderRepositoryImpl;

    public Order createOrder(FoodsListRequestDto request, User user) {
        List<Food> foodList = restaurantService.getFoods(request);
        List<OrderFood> foods = foodList.stream()
                .map(OrderFood::create)
                .toList();
        Order order = Order.create(user, foods);
        return orderRepository.save(order);
    }

}
