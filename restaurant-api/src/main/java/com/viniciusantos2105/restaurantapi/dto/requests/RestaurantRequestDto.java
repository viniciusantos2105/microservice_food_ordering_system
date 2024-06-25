package com.viniciusantos2105.restaurantapi.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

@Value
@Getter
@Setter
public class RestaurantRequestDto implements Serializable {
    @NotBlank(message = "Nome do restaurante não pode ser vazio")
    String restaurantName;
    @NotBlank(message = "Endereço não pode ser vazio")
    String restaurantAddress;
}