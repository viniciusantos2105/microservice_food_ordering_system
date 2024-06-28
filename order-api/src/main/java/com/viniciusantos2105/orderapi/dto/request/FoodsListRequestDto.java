package com.viniciusantos2105.orderapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Value
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class FoodsListRequestDto implements Serializable {
    @NotEmpty(message = "Lista de pratos não pode ser vazia")
    List<UUID> foodsSelecteds;


}