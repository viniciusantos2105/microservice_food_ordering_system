package com.viniciusantos2105.orderapi.dto.response;

import com.viniciusantos2105.orderapi.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderResponseRestaurantDto {

    private UUID orderId;
    private UUID orderUser;
    private List<FoodsReponseDto> orderFoods;
    private Double orderTotalPrice;
    private String orderStatus;

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = OrderStatus.fromString(orderStatus).getDescription();
    }
}
