package com.viniciusantos2105.restaurantapi.dto.requests;

import lombok.Value;

import java.io.Serializable;

@Value
public class FoodEditRequestDto implements Serializable {
    String foodName;
    String foodDescription;
    Double foodPrice;
}
