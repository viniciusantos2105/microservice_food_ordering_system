package com.viniciusantos2105.orderapi.controller;

import com.viniciusantos2105.orderapi.adapter.Adapter;
import com.viniciusantos2105.orderapi.domain.order.OrderHistory;
import com.viniciusantos2105.orderapi.domain.user.User;
import com.viniciusantos2105.orderapi.dto.request.FoodsListRequestDto;
import com.viniciusantos2105.orderapi.dto.request.OrderStatusRequestDto;
import com.viniciusantos2105.orderapi.dto.response.*;
import com.viniciusantos2105.orderapi.service.OrderService;
import com.viniciusantos2105.orderapi.service.RestaurantService;
import com.viniciusantos2105.orderapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final Adapter adapter;
    private final UserService userService;
    private final OrderService orderService;
    private final RestaurantService restaurantService;

    public OrderController(Adapter adapter, UserService userService, OrderService orderService, RestaurantService restaurantService) {
        this.adapter = adapter;
        this.userService = userService;
        this.orderService = orderService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@RequestHeader("Authorization") String token, @PathVariable UUID orderId) {
        User user = userService.getUser(token).block();
        userService.verifyTypeUser(user);
        OrderResponseDto response = adapter.mapSourceToTarget(orderService.findOrderById(orderId, user), OrderResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/status/{orderId}")
    public ResponseEntity<OrderStatusResponseDto> getOrderStatusProgress(@RequestHeader("Authorization") String token, @PathVariable UUID orderId) {
        User user = userService.getUser(token).block();
        userService.verifyTypeUser(user);
        List<OrderHistory> historyList = orderService.findOrderStatusProgress(orderId);
        OrderStatusResponseDto response = new OrderStatusResponseDto();
        List<FoodsReponseDto> foodsResponse = historyList.get(0).getOrder().getOrderFoods().stream()
                .map(food -> adapter.mapSourceToTarget(food, FoodsReponseDto.class)).toList();
        response.setOrderFoods(foodsResponse);
        List<OrderHistoryResponseDto> orderHistoryResponse = historyList.stream()
                .map(history -> adapter.mapSourceToTarget(history, OrderHistoryResponseDto.class)).toList();
        response.setOrderHistory(orderHistoryResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestHeader("Authorization") String token, @RequestBody @Valid FoodsListRequestDto request) {
        User user = userService.getUser(token).block();
        userService.verifyTypeUser(user);
        OrderResponseDto response = adapter.mapSourceToTarget(orderService.createOrder(request, user), OrderResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderResponseRestaurantDto>> getOrdersByRestaurant(@RequestHeader ("Authorization") String token, @PathVariable UUID restaurantId) {
        User user = userService.getUser(token).block();
        userService.verifyOwnerUser(user);
        restaurantService.isUserRestaurantOwner(token, restaurantId);
        List<OrderResponseRestaurantDto> response = orderService.findOrdersByRestaurant(restaurantId).stream().map(order -> adapter.mapSourceToTarget(order, OrderResponseRestaurantDto.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(@RequestHeader("Authorization") String token, @PathVariable UUID orderId, @RequestBody @Valid OrderStatusRequestDto request) {
        User user = userService.getUser(token).block();
        userService.verifyOwnerUser(user);
        OrderResponseDto response = adapter.mapSourceToTarget(orderService.updateOrderStatus(orderId, request, token), OrderResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
