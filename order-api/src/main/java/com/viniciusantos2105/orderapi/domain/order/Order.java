package com.viniciusantos2105.orderapi.domain.order;

import com.viniciusantos2105.orderapi.domain.restaurant.Food;
import com.viniciusantos2105.orderapi.domain.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_user")
    private User orderUser;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_restaurant")
    private Restaurant orderRestaurant;
    @ManyToMany
    @JoinTable(name = "order_food",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> orderFoods;
    private Double orderTotalPrice;
    private OrderStatus orderStatus;

    public static Order create(User user, List<Food> foods) {
        Order order = new Order();
        order.setOrderUser(user);
        order.setOrderRestaurant(foods.get(0).getRestaurant());
        order.setOrderFoods(foods);
        order.setOrderTotalPrice(foods.stream().mapToDouble(Food::getFoodPrice).sum());
        order.setOrderStatus(OrderStatus.PENDING);
        return order;
    }
}
