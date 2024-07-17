package com.viniciusantos2105.orderapi.event;

import com.viniciusantos2105.orderapi.domain.order.entity.Order;
import com.viniciusantos2105.orderapi.domain.order.entity.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    public void notify(Order order, OrderStatus orderStatus) {
        for (EventListener listener : listeners) {
            listener.update(order, orderStatus);
        }
    }
}
