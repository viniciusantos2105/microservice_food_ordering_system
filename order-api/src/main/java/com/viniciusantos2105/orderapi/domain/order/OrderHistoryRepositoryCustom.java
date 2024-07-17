package com.viniciusantos2105.orderapi.domain.order;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryRepositoryCustom {
    List<OrderHistory> findOrderStatusProgress(UUID orderId);
}
