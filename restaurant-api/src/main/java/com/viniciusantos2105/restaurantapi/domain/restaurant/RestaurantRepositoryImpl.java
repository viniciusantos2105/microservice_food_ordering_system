package com.viniciusantos2105.restaurantapi.domain.restaurant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void validateRestaurantName(String restaurantName) {
        TypedQuery<Restaurant> query = entityManager.createQuery("SELECT r FROM Restaurant r WHERE r.restaurantName = :restaurantName", Restaurant.class);
        query.setParameter("restaurantName", restaurantName);

        if (!query.getResultList().isEmpty()) throw new RuntimeException("Restaurante já cadastrado");
    }
}
