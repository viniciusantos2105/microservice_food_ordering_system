package com.viniciusantos2105.restaurantapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodResponseDto {

    private String foodName;
    private String foodDescription;
    private Double foodPrice;
    private RestaurantFoodResponseDto restaurant;
}
