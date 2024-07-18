package com.viniciusantos2105.orderapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class FoodsListRequestDto implements Serializable {
    @NotEmpty(message = "Lista de pratos não pode ser vazia")
    List<UUID> foodsSelecteds;


}