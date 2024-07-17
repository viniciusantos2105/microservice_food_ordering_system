package com.viniciusantos2105.restaurantapi.domain.restaurant.entity;

import com.viniciusantos2105.restaurantapi.domain.food.entity.Food;
import com.viniciusantos2105.restaurantapi.domain.user.entity.User;
import com.viniciusantos2105.restaurantapi.dto.requests.RestaurantRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID restaurantId;
    @JoinColumn(name = "owner_id")
    private UUID ownerId;
    @Column(name = "restaurant_name", unique = true)
    private String restaurantName;
    @Column(name = "restaurant_address")
    private String restaurantAddress;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "restaurant_foods",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> restaurantMenu;

    public static Restaurant create(User user, RestaurantRequestDto request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(user.getUserId());
        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setRestaurantAddress(request.getRestaurantAddress());
        restaurant.setRestaurantMenu(new ArrayList<>());
        return restaurant;
    }
}
