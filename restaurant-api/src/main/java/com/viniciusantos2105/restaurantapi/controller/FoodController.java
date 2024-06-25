package com.viniciusantos2105.restaurantapi.controller;

import com.viniciusantos2105.restaurantapi.adapter.Adapter;
import com.viniciusantos2105.restaurantapi.domain.restaurant.Food;
import com.viniciusantos2105.restaurantapi.domain.user.User;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodRequestDto;
import com.viniciusantos2105.restaurantapi.dto.responses.FoodResponseDto;
import com.viniciusantos2105.restaurantapi.dto.responses.FoodResponseListDto;
import com.viniciusantos2105.restaurantapi.service.FoodService;
import com.viniciusantos2105.restaurantapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{restaurantId}")
    public ResponseEntity<FoodResponseDto> createFood(@RequestHeader("Authorization") String token, @PathVariable Long restaurantId, @RequestBody @Valid FoodRequestDto request) {
        User user = userService.getUser(token).block();
        FoodResponseDto response = adapter.mapSourceToTarget(foodService.createFood(restaurantId, request, user), FoodResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<FoodResponseListDto>> listFoodsByRestaurant(@RequestHeader("Authorization") String token, @PathVariable Long restaurantId) {
        List<Food> foods = foodService.listFoodsByRestaurant(restaurantId, userService.getUser(token).block());
        List<FoodResponseListDto> response = foods.stream().toList().stream().map(food -> adapter.mapSourceToTarget(food, FoodResponseListDto.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
