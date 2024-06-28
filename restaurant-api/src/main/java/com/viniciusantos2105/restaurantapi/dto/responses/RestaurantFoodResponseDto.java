package com.viniciusantos2105.restaurantapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RestaurantFoodResponseDto {

    private UUID restaurantId;
    private String restaurantName;
}
