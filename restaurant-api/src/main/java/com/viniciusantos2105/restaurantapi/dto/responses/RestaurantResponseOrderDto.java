package com.viniciusantos2105.restaurantapi.dto.responses;

import com.viniciusantos2105.restaurantapi.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponseOrderDto {

    private Long restaurantId;
    private User restaurantOwner;
    private String restaurantName;
    private String restaurantAddress;
}
