package com.viniciusantos2105.restaurantapi.controller;

import com.viniciusantos2105.restaurantapi.adapter.Adapter;
import com.viniciusantos2105.restaurantapi.domain.food.contract.IFoodService;
import com.viniciusantos2105.restaurantapi.domain.food.entity.Food;
import com.viniciusantos2105.restaurantapi.domain.user.contract.IUserService;
import com.viniciusantos2105.restaurantapi.domain.user.entity.User;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodEditRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodsListRequestDto;
import com.viniciusantos2105.restaurantapi.dto.responses.FoodResponseDto;
import com.viniciusantos2105.restaurantapi.dto.responses.FoodResponseListDto;
import com.viniciusantos2105.restaurantapi.dto.responses.FoodResponseOrderDto;
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
    private final IUserService userService;
    private final IFoodService foodService;

    public FoodController(IFoodService foodService, Adapter adapter, IUserService userService) {
        this.foodService = foodService;
        this.adapter = adapter;
        this.userService = userService;
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<FoodResponseListDto>> listFoodsByRestaurant(@PathVariable UUID restaurantId) {
        List<Food> foods = foodService.listFoodsByRestaurant(restaurantId);
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
