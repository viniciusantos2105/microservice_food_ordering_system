package com.viniciusantos2105.orderapi.domain.restaurant;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "food")
public class Food implements Serializable {

    @Id
    private Long foodId;
    private String foodName;
    private String foodDescription;
    private Double foodPrice;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "food_restaurant")
    private Restaurant restaurant;

}
