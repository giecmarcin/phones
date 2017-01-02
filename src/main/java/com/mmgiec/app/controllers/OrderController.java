package com.mmgiec.app.controllers;

import com.mmgiec.app.model.Order;
import com.mmgiec.app.model.PhoneAndQuantity;
import com.mmgiec.app.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("api/basket/order/confirm")
    public ResponseEntity<?> confirmOrder(@RequestBody Order order) {
        for (PhoneAndQuantity p : order.getProducts()) {
            System.out.println(p.getPhone().getFullName() + " ilosc" + p.getQuantity());
        }
        boolean isSuccess = orderService.completeOrder(order);
        if (!isSuccess) {
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(order);
    }
}
