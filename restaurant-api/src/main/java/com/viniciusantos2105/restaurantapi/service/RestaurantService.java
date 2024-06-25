package com.viniciusantos2105.restaurantapi.service;

import com.viniciusantos2105.restaurantapi.domain.restaurant.Restaurant;
import com.viniciusantos2105.restaurantapi.domain.restaurant.RestaurantRepository;
import com.viniciusantos2105.restaurantapi.domain.restaurant.RestaurantRepositoryImpl;
import com.viniciusantos2105.restaurantapi.domain.user.User;
import com.viniciusantos2105.restaurantapi.dto.RestaurantRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantRepositoryImpl restaurantRepositoryImpl;

    public Restaurant createRestaurant(User user, RestaurantRequestDto request){
        restaurantRepositoryImpl.validateRestaurantName(request. getRestaurantName());
        Restaurant restaurant = Restaurant.create(user, request);
        return restaurantRepository.save(restaurant);
    }
}
