package com.viniciusantos2105.orderapi.domain.order.entity;

import com.viniciusantos2105.orderapi.domain.user.entity.User;
import com.viniciusantos2105.orderapi.event.EventListener;
import com.viniciusantos2105.orderapi.event.EventManager;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID orderId;
    @JoinColumn(name = "order_user")
    private UUID orderUser;
    @JoinColumn(name = "order_restaurant")
    private UUID orderRestaurant;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderFood> orderFoods;
    private Double orderTotalPrice;
    private OrderStatus orderStatus;

    @Transient
    private EventManager eventManager = new EventManager();

    public static Order create(User user, List<OrderFood> foods) {
        Order order = new Order();
        order.setOrderUser(user.getUserId());
        order.setOrderRestaurant(foods.get(0).getRestaurantId());
        order.setOrderFoods(foods);
        order.setOrderTotalPrice(foods.stream().mapToDouble(OrderFood::getFoodPrice).sum());
        return order;
    }

    public void addListener(EventListener listener) {
        eventManager.subscribe(listener);
    }

    public void removeListener(EventListener listener) {
        eventManager.unsubscribe(listener);
    }

    public void setOrderStatus(Order order, OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        eventManager.notify(order, orderStatus);
    }
}