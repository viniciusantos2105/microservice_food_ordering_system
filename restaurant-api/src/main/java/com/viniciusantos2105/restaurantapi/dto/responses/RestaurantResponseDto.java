package com.viniciusantos2105.restaurantapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RestaurantResponseDto {

    private UUID restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private UUID ownerId;

}
