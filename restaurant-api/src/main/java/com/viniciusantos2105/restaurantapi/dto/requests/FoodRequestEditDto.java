package com.viniciusantos2105.restaurantapi.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.io.Serializable;

@Value
public class FoodRequestEditDto implements Serializable {
    String foodName;
    String foodDescription;
    Double foodPrice;
}
