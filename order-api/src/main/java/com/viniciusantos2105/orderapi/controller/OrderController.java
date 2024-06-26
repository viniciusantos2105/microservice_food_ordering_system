package com.viniciusantos2105.orderapi.controller;

import com.viniciusantos2105.orderapi.adapter.Adapter;
import com.viniciusantos2105.orderapi.domain.order.User;
import com.viniciusantos2105.orderapi.dto.FoodsListRequestDto;
import com.viniciusantos2105.orderapi.dto.OrderResponseDto;
import com.viniciusantos2105.orderapi.service.OrderService;
import com.viniciusantos2105.orderapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final Adapter adapter;
    private final UserService userService;
    private final OrderService orderService;

    public OrderController(Adapter adapter, UserService userService, OrderService orderService) {
        this.adapter = adapter;
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestHeader("Authorization") String token, @RequestBody @Valid FoodsListRequestDto request) {
        User user = userService.getUser(token).block();
        userService.verifyTypeUser(user);
        OrderResponseDto response = adapter.mapSourceToTarget(orderService.createOrder(request, user), OrderResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
