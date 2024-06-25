package com.viniciusantos2105.restaurantapi.domain.restaurant;

import com.viniciusantos2105.restaurantapi.dto.requests.FoodRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;
    private String foodName;
    private String foodDescription;
    private Double foodPrice;
    @ManyToOne(cascade = CascadeType.ALL)
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
