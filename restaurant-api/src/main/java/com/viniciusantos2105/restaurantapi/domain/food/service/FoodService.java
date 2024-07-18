package com.viniciusantos2105.restaurantapi.domain.food.service;


import com.viniciusantos2105.restaurantapi.adapter.Adapter;
import com.viniciusantos2105.restaurantapi.domain.food.entity.Food;
import com.viniciusantos2105.restaurantapi.domain.food.repository.FoodRepository;
import com.viniciusantos2105.restaurantapi.domain.restaurant.entity.Restaurant;
import com.viniciusantos2105.restaurantapi.domain.restaurant.service.RestaurantService;
import com.viniciusantos2105.restaurantapi.domain.user.entity.User;
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

    public Food createFood(UUID restaurantId, FoodRequestDto request, User user) {
        Restaurant restaurant = restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        foodRepository.validateFoodName(request.getFoodName(), restaurantId);
        Food food = Food.create(restaurant, request);
        restaurantService.addFoodToMenu(restaurant, food);
        return foodRepository.save(food);
    }

    public List<Food> findFoodById(FoodsListRequestDto request) {
        List<Food> foodList = request.getFoodsSelecteds().stream()
                .map(foodRepository::findFoodById)
                .toList();
        UUID restaurantId = foodList.get(0).getRestaurant().getRestaurantId();
        if (foodList.stream().anyMatch(food -> !food.getRestaurant().getRestaurantId().equals(restaurantId))) {
            throw InvalidArgumentsException.create("Os alimentos selecionados pertencem a restaurantes diferentes");
        }

        return foodList;
    }

    public List<Food> listFoodsByRestaurant(UUID restaurantId) {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        return restaurant.getRestaurantMenu();
    }

    public Food updateFood(UUID restaurantId, UUID foodId, FoodEditRequestDto request, User user) {
        restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        foodRepository.validateFoodName(request.getFoodName(), restaurantId);
        Food food = foodRepository.findFoodByIdAndRestaurant(restaurantId, foodId);
        update(food, request);
        return foodRepository.save(food);
    }

    private void update(Food food, FoodEditRequestDto request) {
        Food updatedFood = adapter.mapSourceToTarget(request, Food.class);
        adapter.updateTargetWithSource(updatedFood, food);
    }

    public void deleteFood(UUID restaurantId, UUID foodId, User user) {
        Restaurant restaurant = restaurantService.findRestaurantWithUserValidation(restaurantId, user);
        Food food = foodRepository.findFoodByIdAndRestaurant(restaurantId, foodId);
        restaurantService.removeFood(restaurant, food);
        foodRepository.delete(food);
    }
}
