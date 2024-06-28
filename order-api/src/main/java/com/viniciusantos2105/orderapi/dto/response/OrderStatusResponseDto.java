package com.viniciusantos2105.orderapi.dto.response;

import com.viniciusantos2105.orderapi.domain.order.OrderHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderStatusResponseDto {

    private List<FoodsReponseDto> orderFoods;
    private List<OrderHistoryResponseDto> orderHistory;
}
