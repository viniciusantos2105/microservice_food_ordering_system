package com.viniciusantos2105.restaurantapi.dto.requests;

import com.viniciusantos2105.restaurantapi.domain.food.Food;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Food}
 */
@Value
public class FoodRequestDto implements Serializable {
    @NotBlank(message = "Nome não pode ser vazio")
    String foodName;
    String foodDescription;
    @NotNull(message = "Valor não pode ser nulo")
    @Positive
    Double foodPrice;
}