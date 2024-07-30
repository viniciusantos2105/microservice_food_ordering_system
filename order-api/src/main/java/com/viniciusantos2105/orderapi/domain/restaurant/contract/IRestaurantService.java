package com.viniciusantos2105.orderapi.domain.restaurant.contract;

import com.viniciusantos2105.orderapi.domain.restaurant.entity.Food;
import com.viniciusantos2105.orderapi.dto.request.FoodsListRequestDto;

import java.util.List;
import java.util.UUID;

public interface IRestaurantService {
    List<Food> getFoods(FoodsListRequestDto request);
    void isUserRestaurantOwner(String token, UUID restaurantId);
}
