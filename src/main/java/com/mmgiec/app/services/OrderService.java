package com.mmgiec.app.services;


import com.mmgiec.app.model.Order;

public interface OrderService {
    boolean completeOrder(Order order);
}
