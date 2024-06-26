package com.viniciusantos2105.restaurantapi.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
@NoArgsConstructor(force = true)
public class FoodsListRequestDto implements Serializable {
    @NotEmpty(message = "Lista de pratos não pode ser vazia")
    List<Long> foodsSelecteds;
}
