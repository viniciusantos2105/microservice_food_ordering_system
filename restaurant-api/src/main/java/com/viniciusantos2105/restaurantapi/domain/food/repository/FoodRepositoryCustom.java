package com.viniciusantos2105.restaurantapi.domain.food.repository;

import com.viniciusantos2105.restaurantapi.domain.food.entity.Food;

import java.util.UUID;

public interface FoodRepositoryCustom {

    void validateFoodName(String foodName, UUID restaurantId);
    Food findFoodByIdAndRestaurant(UUID restaurantId, UUID foodId);
    Food findFoodById(UUID fooId);
}
