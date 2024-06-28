package com.viniciusantos2105.restaurantapi.domain.restaurant;

import com.viniciusantos2105.restaurantapi.exception.resource.ResourceAlreadyExists;
import com.viniciusantos2105.restaurantapi.exception.resource.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RestaurantRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void validateRestaurantName(String restaurantName) {
        TypedQuery<Restaurant> query = entityManager.createQuery("SELECT r FROM Restaurant r WHERE r.restaurantName = :restaurantName", Restaurant.class);
        query.setParameter("restaurantName", restaurantName);

        if (!query.getResultList().isEmpty())
            throw ResourceAlreadyExists.create("Já existe um restaurante com esse nome", "restaurantName");
    }

    public Restaurant findRestaurantById(UUID restaurantId) {
        TypedQuery<Restaurant> query = entityManager.createQuery("SELECT r FROM Restaurant r WHERE r.restaurantId = :restaurantId", Restaurant.class);
        query.setParameter("restaurantId", restaurantId);

        if (query.getResultList().isEmpty()) {
            throw ResourceNotFoundException.create("Restaurante não encontrado", "restaurantId", 404);
        }

        return query.getSingleResult();
    }
}
