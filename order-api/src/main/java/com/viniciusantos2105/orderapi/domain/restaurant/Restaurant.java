package com.viniciusantos2105.orderapi.domain.restaurant;

import com.viniciusantos2105.orderapi.domain.order.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    private Long restaurantId;
    @Column(name = "restaurant_name", unique = true)
    private String restaurantName;
    @Column(name = "restaurant_address")
    private String restaurantAddress;

}
