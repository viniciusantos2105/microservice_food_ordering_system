package com.viniciusantos2105.restaurantapi.domain.restaurant.repository;

import com.viniciusantos2105.restaurantapi.domain.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
}
