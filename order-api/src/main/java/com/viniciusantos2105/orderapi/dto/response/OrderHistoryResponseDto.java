package com.viniciusantos2105.orderapi.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;


@Getter
@Setter
public class OrderHistoryResponseDto {

    private String orderStatus;
    private String orderStatusDate;

    public void setOrderStatusDate(Timestamp orderStatusDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        this.orderStatusDate = dateFormat.format(orderStatusDate);
    }
}
