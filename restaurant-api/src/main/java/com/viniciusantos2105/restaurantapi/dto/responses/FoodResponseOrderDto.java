package com.viniciusantos2105.restaurantapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodResponseOrderDto {

    private Long foodId;
    private String foodName;
    private String foodDescription;
    private Double foodPrice;
    private RestaurantResponseOrderDto restaurant;
}