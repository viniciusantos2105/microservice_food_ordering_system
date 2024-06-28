package com.viniciusantos2105.restaurantapi.service;


import com.viniciusantos2105.restaurantapi.adapter.Adapter;
import com.viniciusantos2105.restaurantapi.domain.restaurant.Food;
import com.viniciusantos2105.restaurantapi.domain.restaurant.FoodRepository;
import com.viniciusantos2105.restaurantapi.domain.restaurant.FoodRepositoryImpl;
import com.viniciusantos2105.restaurantapi.domain.restaurant.Restaurant;
import com.viniciusantos2105.restaurantapi.domain.user.User;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodEditRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodsListRequestDto;
import com.viniciusantos2105.restaurantapi.exception.validation.InvalidArgumentsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final Adapter adapter;
    private final RestaurantService restaurantService;
    private final FoodRepository foodRepository;
    private final FoodRepositoryImpl foodRepositoryImpl;

    public Food createFood(UUID restaurantId, FoodRequestDto request, User user) {
        Restaurant restaurant = restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        foodRepositoryImpl.validateFoodName(request.getFoodName(), restaurantId);
        Food food = Food.create(restaurant, request);
        restaurantService.addFoodToMenu(restaurant, food);
        return foodRepository.save(food);
    }

    public List<Food> findFoodById(FoodsListRequestDto request) {
        List<Food> foodList = request.getFoodsSelecteds().stream()
                .map(foodRepositoryImpl::findFoodById)
                .toList();
        UUID restaurantId = foodList.get(0).getRestaurant().getRestaurantId();
        if (foodList.stream().anyMatch(food -> !food.getRestaurant().getRestaurantId().equals(restaurantId))) {
            throw InvalidArgumentsException.create("Os alimentos selecionados pertencem a restaurantes diferentes");
        }

        return foodList;
    }

    public List<Food> listFoodsByRestaurant(UUID restaurantId, User user) {
        Restaurant restaurant = restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        return restaurant.getRestaurantMenu();
    }

    public Food updateFood(UUID restaurantId, UUID foodId, FoodEditRequestDto request, User user) {
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

    public void deleteFood(UUID restaurantId, UUID foodId, User user) {
        Restaurant restaurant = restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        Food food = foodRepositoryImpl.findFoodByIdAndRestaurant(restaurantId, foodId);
        restaurantService.removeFood(restaurant, food);
        foodRepository.delete(food);
    }
}
