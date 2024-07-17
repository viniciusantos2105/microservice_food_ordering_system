package com.viniciusantos2105.restaurantapi.domain.restaurant;

import java.util.UUID;

public interface RestaurantRepositoryCustom{

    void validateRestaurantName(String restaurantName);
    Restaurant findRestaurantById(UUID restaurantId);
}
