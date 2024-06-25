package com.viniciusantos2105.restaurantapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseDto {

    private String restaurantName;
    private String restaurantAddress;
    private UserResponseDto restaurantOwner;

}
