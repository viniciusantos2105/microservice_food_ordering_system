package com.viniciusantos2105.restaurantapi.domain.restaurant.repository;

import com.viniciusantos2105.restaurantapi.domain.restaurant.entity.Restaurant;

import java.util.UUID;

public interface RestaurantRepositoryCustom{

    void validateRestaurantName(String restaurantName);
    Restaurant findRestaurantById(UUID restaurantId);
}
