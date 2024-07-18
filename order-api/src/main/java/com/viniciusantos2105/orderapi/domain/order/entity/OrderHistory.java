package com.viniciusantos2105.orderapi.domain.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "order_history")
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID orderHistoryId;
    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;
    private OrderStatus orderStatus;
    private Timestamp orderStatusDate;

    public static OrderHistory create(Order order, OrderStatus orderStatus) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrder(order);
        orderHistory.setOrderStatus(orderStatus);
        orderHistory.setOrderStatusDate(new Timestamp(System.currentTimeMillis()));
        return orderHistory;
    }
}
