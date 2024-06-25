package com.viniciusantos2105.restaurantapi.domain.restaurant;

import com.viniciusantos2105.restaurantapi.domain.user.User;
import com.viniciusantos2105.restaurantapi.dto.RestaurantRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_owner")
    private User restaurantOwner;
    @Column(name = "restaurant_name", unique = true)
    private String restaurantName;
    @Column(name = "restaurant_address")
    private String restaurantAddress;
    @OneToMany
    @JoinTable(
        name = "restaurant_foods",
        joinColumns = @JoinColumn(name = "restaurant_id"),
        inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> restaurantMenu;

    public static Restaurant create(User user, RestaurantRequestDto request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantOwner(user);
        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setRestaurantAddress(request.getRestaurantAddress());
        restaurant.setRestaurantMenu(new ArrayList<>());
        return restaurant;
    }
}
