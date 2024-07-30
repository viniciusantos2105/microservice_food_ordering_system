package com.viniciusantos2105.restaurantapi.domain.food.contract;

import com.viniciusantos2105.restaurantapi.domain.food.entity.Food;
import com.viniciusantos2105.restaurantapi.domain.user.entity.User;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodEditRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodsListRequestDto;

import java.util.List;
import java.util.UUID;

public interface IFoodService {

    Food createFood(UUID restaurantId, FoodRequestDto request, User user);
    List<Food> findFoodById(FoodsListRequestDto request);
    List<Food> listFoodsByRestaurant(UUID restaurantId);
    Food updateFood(UUID restaurantId, UUID foodId, FoodEditRequestDto request, User user);
    void deleteFood(UUID restaurantId, UUID foodId, User user);
}
