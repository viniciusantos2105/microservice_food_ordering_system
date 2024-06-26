package com.viniciusantos2105.orderapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    private Long orderId;
    private String orderUser;
    private String orderRestaurant;
    private List<FoodsReponseDto> orderFoods;
    private Double orderTotalPrice;
}
