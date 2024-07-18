package com.viniciusantos2105.restaurantapi.domain.food.entity;

import com.viniciusantos2105.restaurantapi.domain.restaurant.entity.Restaurant;
import com.viniciusantos2105.restaurantapi.dto.requests.FoodRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID foodId;
    @Column(name = "food_name")
    private String foodName;
    @Column(name = "food_description")
    private String foodDescription;
    @Column(name = "food_price")
    private Double foodPrice;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public static Food create(Restaurant restaurant, FoodRequestDto request) {
        Food food = new Food();
        food.setFoodName(request.getFoodName());
        food.setFoodDescription(request.getFoodDescription());
        food.setFoodPrice(request.getFoodPrice());
        food.setRestaurant(restaurant);
        return food;
    }
}
