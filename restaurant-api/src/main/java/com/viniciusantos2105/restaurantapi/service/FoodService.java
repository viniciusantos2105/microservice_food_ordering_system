package com.viniciusantos2105.restaurantapi.service;


import com.viniciusantos2105.restaurantapi.domain.restaurant.Food;
import com.viniciusantos2105.restaurantapi.domain.restaurant.FoodRepository;
import com.viniciusantos2105.restaurantapi.domain.restaurant.FoodRepositoryImpl;
import com.viniciusantos2105.restaurantapi.domain.restaurant.Restaurant;
import com.viniciusantos2105.restaurantapi.domain.user.User;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final RestaurantService restaurantService;
    private final FoodRepository foodRepository;
    private final FoodRepositoryImpl foodRepositoryImpl;

    public Food createFood(Long restaurantId, FoodRequestDto request, User user) {
        Restaurant restaurant = restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        foodRepositoryImpl.validateFoodName(request.getFoodName(), restaurantId);
        Food food = Food.create(restaurant, request);
        restaurant.getRestaurantMenu().add(food);
        return foodRepository.save(food);
    }

    public List<Food> listFoodsByRestaurant(Long restaurantId, User user) {
        Restaurant restaurant = restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        return restaurant.getRestaurantMenu();
    }
}
