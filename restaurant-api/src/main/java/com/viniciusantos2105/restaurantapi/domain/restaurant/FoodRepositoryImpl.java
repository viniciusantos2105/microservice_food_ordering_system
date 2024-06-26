package com.viniciusantos2105.restaurantapi.domain.restaurant;

import com.viniciusantos2105.restaurantapi.exception.resource.ResourceAlreadyExists;
import com.viniciusantos2105.restaurantapi.exception.resource.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class FoodRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public void validateFoodName(String foodName, Long restaurantId) {
        TypedQuery<Food> query = entityManager.createQuery("SELECT f FROM Food f WHERE f.foodName = :foodName " +
                "AND f.restaurant.restaurantId = :restaurantId ", Food.class);
        query.setParameter("foodName", foodName);
        query.setParameter("restaurantId", restaurantId);


        if (!query.getResultList().isEmpty())
            throw ResourceAlreadyExists.create("Já existe um prato com esse nome", "foodName");
    }

    public Food findFoodByIdAndRestaurant(Long restaurantId, Long foodId) {
        TypedQuery<Food> query = entityManager.createQuery("SELECT f FROM Food f WHERE f.foodId = :foodId " +
                "AND f.restaurant.restaurantId = :restaurantId ", Food.class);
        query.setParameter("foodId", foodId);
        query.setParameter("restaurantId", restaurantId);

        if (query.getResultList().isEmpty()) {
            throw ResourceNotFoundException.create("Prato não encontrado", "foodId");
        }

        return query.getSingleResult();
    }

    public Food findFoodById(Long fooId){
        TypedQuery<Food> query = entityManager.createQuery("SELECT f FROM Food f WHERE f.foodId = :foodId", Food.class);
        query.setParameter("foodId", fooId);

        if (query.getResultList().isEmpty()) {
            throw ResourceNotFoundException.create("Prato não encontrado", "foodId");
        }

        return query.getSingleResult();
    }
}
