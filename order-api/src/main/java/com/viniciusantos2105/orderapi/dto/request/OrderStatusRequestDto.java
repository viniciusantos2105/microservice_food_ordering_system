package com.viniciusantos2105.orderapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusRequestDto {
    @NotEmpty(message = "Status do pedido não pode ser vazio")
    private String orderStatus;
}
