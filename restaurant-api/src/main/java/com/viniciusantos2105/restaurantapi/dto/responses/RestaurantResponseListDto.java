package com.viniciusantos2105.restaurantapi.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseListDto {
    private Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;
}
