package com.viniciusantos2105.restaurantapi.controller;

import com.viniciusantos2105.restaurantapi.adapter.Adapter;
import com.viniciusantos2105.restaurantapi.domain.restaurant.entity.Restaurant;
import com.viniciusantos2105.restaurantapi.domain.user.entity.User;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantEditRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantRequestDto;
import com.viniciusantos2105.restaurantapi.dto.responses.RestaurantResponseDto;
import com.viniciusantos2105.restaurantapi.dto.responses.RestaurantResponseListDto;
import com.viniciusantos2105.restaurantapi.domain.restaurant.service.RestaurantService;
import com.viniciusantos2105.restaurantapi.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<RestaurantResponseListDto>> listRestaurants() {
        List<Restaurant> restaurantList = restaurantService.listRestaurants();
        List<RestaurantResponseListDto> responseList = restaurantList.stream().toList().stream().map(restaurant -> adapter.mapSourceToTarget(restaurant, RestaurantResponseListDto.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> getRestaurant(@RequestHeader("Authorization") String token, @PathVariable UUID restaurantId) {
        User user = userService.getUser(token).block();
        userService.validateOwnerUser(user);
        restaurantService.validateRestaurantOwner(restaurantId, user.getUserId());
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestHeader("Authorization") String token, @RequestBody @Valid RestaurantRequestDto request) {
        User user = userService.getUser(token).block();
        userService.validateOwnerUser(user);
        RestaurantResponseDto response = adapter.mapSourceToTarget(restaurantService.createRestaurant(request, user), RestaurantResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> updateRestaurant(@RequestHeader("Authorization") String token, @PathVariable UUID restaurantId, @RequestBody @Valid RestaurantEditRequestDto request) {
        User user = userService.getUser(token).block();
        RestaurantResponseDto response = adapter.mapSourceToTarget(restaurantService.updateRestaurant(restaurantId, request, user), RestaurantResponseDto.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@RequestHeader("Authorization") String token, @PathVariable UUID restaurantId) {
        User user = userService.getUser(token).block();
        restaurantService.deleteRestaurant(restaurantId, user);
        return ResponseEntity.noContent().build();
    }
}
