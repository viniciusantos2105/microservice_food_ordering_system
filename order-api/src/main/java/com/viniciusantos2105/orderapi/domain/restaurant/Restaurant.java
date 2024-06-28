package com.viniciusantos2105.orderapi.domain.restaurant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Restaurant {

    private UUID restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private UUID ownerId;
}
