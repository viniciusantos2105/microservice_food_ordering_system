package com.viniciusantos2105.restaurantapi.service;


import com.viniciusantos2105.restaurantapi.adapter.Adapter;
import com.viniciusantos2105.restaurantapi.domain.restaurant.Food;
import com.viniciusantos2105.restaurantapi.domain.restaurant.FoodRepository;
import com.viniciusantos2105.restaurantapi.domain.restaurant.FoodRepositoryImpl;
import com.viniciusantos2105.restaurantapi.domain.restaurant.Restaurant;
import com.viniciusantos2105.restaurantapi.domain.user.User;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodEditRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final Adapter adapter;
    private final RestaurantService restaurantService;
    private final FoodRepository foodRepository;
    private final FoodRepositoryImpl foodRepositoryImpl;

    public Food createFood(Long restaurantId, FoodRequestDto request, User user) {
        Restaurant restaurant = restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        foodRepositoryImpl.validateFoodName(request.getFoodName(), restaurantId);
        Food food = Food.create(restaurant, request);
        restaurantService.addFoodToMenu(restaurant, food);
        return foodRepository.save(food);
    }

    public List<Food> listFoodsByRestaurant(Long restaurantId, User user) {
        Restaurant restaurant = restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        return restaurant.getRestaurantMenu();
    }

    public Food updateFood(Long restaurantId, Long foodId, FoodEditRequestDto request, User user) {
        restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        foodRepositoryImpl.validateFoodName(request.getFoodName(), restaurantId);
        Food food = foodRepositoryImpl.findFoodByIdAndRestaurant(restaurantId, foodId);
        update(food, request);
        return foodRepository.save(food);
    }

    private void update(Food food, FoodEditRequestDto request) {
        Food updatedFood = adapter.mapSourceToTarget(request, Food.class);
        adapter.updateTargetWithSource(updatedFood, food);
    }

    public void deleteFood(Long restaurantId, Long foodId, User user) {
        Restaurant restaurant = restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        Food food = foodRepositoryImpl.findFoodByIdAndRestaurant(restaurantId, foodId);
        restaurantService.removeFood(restaurant, food);
        foodRepository.delete(food);
    }
}
