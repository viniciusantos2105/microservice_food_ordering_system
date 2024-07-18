package com.viniciusantos2105.orderapi.domain.order.repository;

import com.viniciusantos2105.orderapi.domain.order.entity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long>, OrderHistoryRepositoryCustom {
}
