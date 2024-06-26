package com.viniciusantos2105.restaurantapi.controller;

import com.viniciusantos2105.restaurantapi.adapter.Adapter;
import com.viniciusantos2105.restaurantapi.domain.user.User;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantEditRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantRequestDto;
import com.viniciusantos2105.restaurantapi.dto.responses.RestaurantResponseDto;
import com.viniciusantos2105.restaurantapi.service.RestaurantService;
import com.viniciusantos2105.restaurantapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;
    private final Adapter adapter;

    public RestaurantController(RestaurantService restaurantService, UserService userService, Adapter adapter) {
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.adapter = adapter;
    }

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestHeader("Authorization") String token, @RequestBody @Valid RestaurantRequestDto request) {
        User user = userService.getUser(token).block();
        RestaurantResponseDto response = adapter.mapSourceToTarget(restaurantService.createRestaurant(request, user), RestaurantResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> updateRestaurant(@RequestHeader("Authorization") String token, @PathVariable Long restaurantId, @RequestBody @Valid RestaurantEditRequestDto request) {
        User user = userService.getUser(token).block();
        RestaurantResponseDto response = adapter.mapSourceToTarget(restaurantService.updateRestaurant(restaurantId, request, user), RestaurantResponseDto.class);
        return ResponseEntity.ok(response);
    }
}
