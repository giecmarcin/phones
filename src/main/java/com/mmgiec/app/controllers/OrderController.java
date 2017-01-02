package com.mmgiec.app.controllers;

import com.mmgiec.app.model.Order;
import com.mmgiec.app.model.PhoneAndQuantity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PostMapping("api/basket/order/confirm")
    public void confirmOrder(@RequestBody Order order) {
        for (PhoneAndQuantity p : order.getProducts()) {
            System.out.println(p.getPhone().getFullName() + " ilosc" + p.getQuantity());
        }
    }
}
