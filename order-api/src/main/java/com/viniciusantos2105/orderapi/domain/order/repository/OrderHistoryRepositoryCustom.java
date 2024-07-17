package com.viniciusantos2105.orderapi.domain.order.repository;

import com.viniciusantos2105.orderapi.domain.order.entity.OrderHistory;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryRepositoryCustom {
    List<OrderHistory> findOrderStatusProgress(UUID orderId);
}
