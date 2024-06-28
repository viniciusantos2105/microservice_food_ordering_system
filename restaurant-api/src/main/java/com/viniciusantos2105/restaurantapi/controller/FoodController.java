package com.viniciusantos2105.restaurantapi.controller;

import com.viniciusantos2105.restaurantapi.adapter.Adapter;
import com.viniciusantos2105.restaurantapi.domain.restaurant.Food;
import com.viniciusantos2105.restaurantapi.domain.user.User;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodEditRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodsListRequestDto;
import com.viniciusantos2105.restaurantapi.dto.responses.FoodResponseDto;
import com.viniciusantos2105.restaurantapi.dto.responses.FoodResponseListDto;
import com.viniciusantos2105.restaurantapi.dto.responses.FoodResponseOrderDto;
import com.viniciusantos2105.restaurantapi.service.FoodService;
import com.viniciusantos2105.restaurantapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurant/food")
public class FoodController {

    private final Adapter adapter;
    private final UserService userService;
    private final FoodService foodService;

    public FoodController(FoodService foodService, Adapter adapter, UserService userService) {
        this.foodService = foodService;
        this.adapter = adapter;
        this.userService = userService;
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<FoodResponseListDto>> listFoodsByRestaurant(@RequestHeader("Authorization") String token, @PathVariable UUID restaurantId) {
        List<Food> foods = foodService.listFoodsByRestaurant(restaurantId, userService.getUser(token).block());
        List<FoodResponseListDto> response = foods.stream().toList().stream().map(food -> adapter.mapSourceToTarget(food, FoodResponseListDto.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<List<FoodResponseOrderDto>> findFoods(@RequestBody @Valid FoodsListRequestDto request) {
        List<Food> foods = foodService.findFoodById(request);
        List<FoodResponseOrderDto> response = foods.stream().toList().stream().map(food -> adapter.mapSourceToTarget(food, FoodResponseOrderDto.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<FoodResponseDto> createFood(@RequestHeader("Authorization") String token, @PathVariable UUID restaurantId, @RequestBody @Valid FoodRequestDto request) {
        User user = userService.getUser(token).block();
        FoodResponseDto response = adapter.mapSourceToTarget(foodService.createFood(restaurantId, request, user), FoodResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{restaurantId}/{foodId}")
    public ResponseEntity<FoodResponseDto> updateFood(@RequestHeader("Authorization") String token, @PathVariable UUID restaurantId, @PathVariable UUID foodId, @RequestBody @Valid FoodEditRequestDto request) {
        User user = userService.getUser(token).block();
        FoodResponseDto response = adapter.mapSourceToTarget(foodService.updateFood(restaurantId, foodId, request, user), FoodResponseDto.class);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{restaurantId}/{foodId}")
    public ResponseEntity<Void> deleteFood(@RequestHeader("Authorization") String token, @PathVariable UUID restaurantId, @PathVariable UUID foodId) {
        User user = userService.getUser(token).block();
        foodService.deleteFood(restaurantId, foodId, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
