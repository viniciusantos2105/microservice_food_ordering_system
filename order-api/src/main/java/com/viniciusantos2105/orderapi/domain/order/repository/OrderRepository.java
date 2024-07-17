package com.viniciusantos2105.orderapi.domain.order.repository;

import com.viniciusantos2105.orderapi.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
