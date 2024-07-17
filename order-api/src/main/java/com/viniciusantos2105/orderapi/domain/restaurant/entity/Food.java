package com.viniciusantos2105.orderapi.domain.restaurant.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class Food implements Serializable {

    private String foodName;
    private String foodDescription;
    private Double foodPrice;
    private UUID restaurantId;

}
