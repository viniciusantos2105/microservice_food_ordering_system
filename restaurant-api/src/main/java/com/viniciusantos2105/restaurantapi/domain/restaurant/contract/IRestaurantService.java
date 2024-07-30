package com.viniciusantos2105.restaurantapi.domain.restaurant.contract;

import com.viniciusantos2105.restaurantapi.domain.food.entity.Food;
import com.viniciusantos2105.restaurantapi.domain.restaurant.entity.Restaurant;
import com.viniciusantos2105.restaurantapi.domain.user.entity.User;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantEditRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantRequestDto;

import java.util.List;
import java.util.UUID;

public interface IRestaurantService {

    List<Restaurant> listRestaurants();
    Restaurant findRestaurantWithUserValidation(UUID restaurantId, User user);
    Restaurant findRestaurantById(UUID restaurantId);
    void validateRestaurantOwner(UUID restaurantId, UUID ownerId);
    Restaurant createRestaurant(RestaurantRequestDto request, User user);
    Restaurant updateRestaurant(UUID restaurantId, RestaurantEditRequestDto request, User user);
    void deleteRestaurant(UUID restaurantId, User user);
    void addFoodToMenu(Restaurant restaurant, Food food);
    void removeFood(Restaurant restaurant, Food food);
}
