package com.viniciusantos2105.restaurantapi.domain.food.repository;

import com.viniciusantos2105.restaurantapi.domain.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>, FoodRepositoryCustom {
}
