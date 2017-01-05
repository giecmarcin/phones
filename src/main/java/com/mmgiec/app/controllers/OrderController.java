package com.mmgiec.app.controllers;

import com.mmgiec.app.entities.HistoryOfOrders;
import com.mmgiec.app.entities.User;
import com.mmgiec.app.model.Order;
import com.mmgiec.app.model.PhoneAndQuantity;
import com.mmgiec.app.services.HistoryService;
import com.mmgiec.app.services.OrderService;
import com.mmgiec.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;

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

    @GetMapping("api/orders/all/{email}")
    private ResponseEntity<?> findAllOrders(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            List<HistoryOfOrders> orders = historyService.findByUser(user.get());
            if (orders.isEmpty())
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
            return ResponseEntity.ok(orders);
        } else {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }
}
