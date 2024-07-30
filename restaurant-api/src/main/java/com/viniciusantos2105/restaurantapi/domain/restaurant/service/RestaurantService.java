package com.viniciusantos2105.restaurantapi.domain.restaurant.service;

import com.viniciusantos2105.restaurantapi.adapter.Adapter;
import com.viniciusantos2105.restaurantapi.domain.food.entity.Food;
import com.viniciusantos2105.restaurantapi.domain.restaurant.contract.IRestaurantService;
import com.viniciusantos2105.restaurantapi.domain.restaurant.entity.Restaurant;
import com.viniciusantos2105.restaurantapi.domain.restaurant.repository.RestaurantRepository;
import com.viniciusantos2105.restaurantapi.domain.user.entity.User;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantEditRequestDto;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantRequestDto;
import com.viniciusantos2105.restaurantapi.exception.unauthorized.UnauthorizedAcessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantService implements IRestaurantService {

    private final Adapter adapter;
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> listRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findRestaurantWithUserValidation(UUID restaurantId, User user) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(restaurantId);
        if (!restaurant.getOwnerId().equals(user.getUserId())) {
            throw UnauthorizedAcessException.create("Usuario não tem acesso a esse restaurante", "restaurantId");
        }
        return restaurant;
    }

    @Override
    public Restaurant findRestaurantById(UUID restaurantId) {
        return restaurantRepository.findRestaurantById(restaurantId);
    }

    @Override
    public void validateRestaurantOwner(UUID restaurantId, UUID ownerId) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(restaurantId);
        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw UnauthorizedAcessException.create("Usuario não tem acesso a esse restaurante", "restaurantId");
        }
    }

    @Override
    public Restaurant createRestaurant(RestaurantRequestDto request, User user) {
        restaurantRepository.validateRestaurantName(request.getRestaurantName());
        Restaurant restaurant = Restaurant.create(user, request);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(UUID restaurantId, RestaurantEditRequestDto request, User user) {
        Restaurant restaurant = findRestaurantWithUserValidation(restaurantId, user);
        update(restaurant, request);
        return restaurantRepository.save(restaurant);
    }

    private void update(Restaurant restaurant, RestaurantEditRequestDto request) {
        Restaurant updatedRestaurant = adapter.mapSourceToTarget(request, Restaurant.class);
        adapter.updateTargetWithSource(updatedRestaurant, restaurant);
    }

    @Override
    public void deleteRestaurant(UUID restaurantId, User user) {
        Restaurant restaurant = findRestaurantWithUserValidation(restaurantId, user);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public void addFoodToMenu(Restaurant restaurant, Food food) {
        restaurant.getRestaurantMenu().add(food);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void removeFood(Restaurant restaurant, Food food) {
        restaurant.getRestaurantMenu().remove(food);
        restaurantRepository.save(restaurant);
    }
}
