package com.viniciusantos2105.restaurantapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FoodResponseOrderDto {

    private UUID foodId;
    private String foodName;
    private String foodDescription;
    private Double foodPrice;
    private UUID restaurantId;
}
