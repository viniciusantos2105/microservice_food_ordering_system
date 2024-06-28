package com.viniciusantos2105.orderapi.domain.order;

import com.viniciusantos2105.orderapi.exception.resource.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public Order findOrderById(UUID orderId) {
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o WHERE o.orderId = :orderId", Order.class);
        query.setParameter("orderId", orderId);

        if(query.getResultList().isEmpty()) {
            throw ResourceNotFoundException.create("Pedido não encontrado", "orderId");
        }
        return query.getSingleResult();
    }

    public List<Order> findOrdersByRestaurant(UUID restaurantId) {
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o WHERE o.orderRestaurant = :restaurantId", Order.class);
        query.setParameter("restaurantId", restaurantId);

        return query.getResultList();
    }
}
