package com.viniciusantos2105.restaurantapi.dto.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Value
@Getter
@Setter
public class RestaurantEditRequestDto implements Serializable {
    String restaurantName;
    String restaurantAddress;
}
