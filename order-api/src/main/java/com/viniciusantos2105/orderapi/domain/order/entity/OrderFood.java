package com.viniciusantos2105.orderapi.domain.order.entity;

import com.viniciusantos2105.orderapi.domain.restaurant.entity.Food;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "order_food")
public class OrderFood implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID orderFoodId;
    @Column(name = "order_id")
    private UUID orderId;
    @Column(name = "food_name")
    private String foodName;
    @Column(name = "food_description")
    private String foodDescription;
    @Column(name = "food_price")
    private Double foodPrice;
    @Column(name = "restaurant_id")
    private UUID restaurantId;

    public static OrderFood create(Food food) {
        OrderFood orderFood = new OrderFood();
        orderFood.setFoodName(food.getFoodName());
        orderFood.setFoodDescription(food.getFoodDescription());
        orderFood.setFoodPrice(food.getFoodPrice());
        orderFood.setRestaurantId(food.getRestaurantId());
        return orderFood;
    }
}