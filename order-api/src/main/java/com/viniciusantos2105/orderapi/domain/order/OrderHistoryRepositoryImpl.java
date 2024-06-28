package com.viniciusantos2105.orderapi.domain.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderHistoryRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<OrderHistory> findOrderStatusProgress(UUID orderId) {
        TypedQuery<OrderHistory> query = entityManager.createQuery("SELECT oh FROM OrderHistory oh WHERE oh.order.orderId = :orderId", OrderHistory.class);
        query.setParameter("orderId", orderId);

        return query.getResultList();
    }
}
