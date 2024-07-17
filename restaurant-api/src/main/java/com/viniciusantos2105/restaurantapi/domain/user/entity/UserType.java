package com.viniciusantos2105.restaurantapi.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserType {

    OWNER("Dono"),
    CLIENT("Cliente");

    private final String description;

    public static UserType fromString(String userType) {
        return Arrays.stream(UserType.values())
                .filter(value -> value.name().equalsIgnoreCase(userType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de usuario invalido"));
    }
}
