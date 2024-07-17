package com.viniciusantos2105.orderapi.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderStatusResponseDto {

    private List<FoodsReponseDto> orderFoods;
    private List<OrderHistoryResponseDto> orderHistory;
}
