package com.viniciusantos2105.restaurantapi.domain.food;

import java.util.UUID;

public interface FoodRepositoryCustom {

    void validateFoodName(String foodName, UUID restaurantId);
    Food findFoodByIdAndRestaurant(UUID restaurantId, UUID foodId);
    Food findFoodById(UUID fooId);
}
