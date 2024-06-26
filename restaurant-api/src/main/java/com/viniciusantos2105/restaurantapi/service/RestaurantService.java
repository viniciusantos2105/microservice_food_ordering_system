package com.viniciusantos2105.restaurantapi.service;

import com.viniciusantos2105.restaurantapi.adapter.Adapter;
import com.viniciusantos2105.restaurantapi.domain.restaurant.Food;
import com.viniciusantos2105.restaurantapi.domain.restaurant.Restaurant;
import com.viniciusantos2105.restaurantapi.domain.restaurant.RestaurantRepository;
import com.viniciusantos2105.restaurantapi.domain.restaurant.RestaurantRepositoryImpl;
import com.viniciusantos2105.restaurantapi.domain.user.User;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantEditRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantRequestDto;
import com.viniciusantos2105.restaurantapi.exception.unauthorized.UnauthorizedAcessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final Adapter adapter;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantRepositoryImpl restaurantRepositoryImpl;

    public Restaurant createRestaurant(RestaurantRequestDto request, User user) {
        restaurantRepositoryImpl.validateRestaurantName(request.getRestaurantName());
        Restaurant restaurant = Restaurant.create(user, request);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long restaurantId, RestaurantEditRequestDto request, User user) {
        Restaurant restaurant = findRestaurantWithUserValidation(restaurantId, user);
        update(restaurant, request);
        return restaurantRepository.save(restaurant);
    }

    private void update(Restaurant restaurant, RestaurantEditRequestDto request){
        Restaurant updatedRestaurant = adapter.mapSourceToTarget(request, Restaurant.class);
        adapter.updateTargetWithSource(updatedRestaurant, restaurant);
    }

    public Restaurant findRestaurantWithUserValidation(Long restaurantId, User user) {
        Restaurant restaurant = restaurantRepositoryImpl.findRestaurantById(restaurantId);
        if (!restaurant.getRestaurantOwner().getUserId().equals(user.getUserId())) {
            throw UnauthorizedAcessException.create("Usuario não tem acesso a esse restaurante", "restaurantId");
        }
        return restaurant;
    }

    public void removeFood(Restaurant restaurant, Food food){
        restaurant.getRestaurantMenu().remove(food);
        restaurantRepository.save(restaurant);
    }
}
