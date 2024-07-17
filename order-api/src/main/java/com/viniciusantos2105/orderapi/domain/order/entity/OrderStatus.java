package com.viniciusantos2105.orderapi.domain.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING("Pendente"),
    IN_PREPARATION("Em preparo"),
    IN_DELIVERY("Rota de entrega"),
    DELIVERED("Entregue");

    private final String description;

    public static OrderStatus fromString(String orderStatus) {
        return Arrays.stream(OrderStatus.values())
                .filter(value -> value.name().equalsIgnoreCase(orderStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de status invalido"));
    }
}
