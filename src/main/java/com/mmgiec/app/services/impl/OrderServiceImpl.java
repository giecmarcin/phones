package com.mmgiec.app.services.impl;

import com.mmgiec.app.model.Order;
import com.mmgiec.app.model.PhoneAndQuantity;
import com.mmgiec.app.repositories.WarehouseRepository;
import com.mmgiec.app.services.OrderService;
import com.mmgiec.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private UserService userService;

    @Override
    public boolean completeOrder(Order order) {
        boolean isSuccess = true;
        Map<PhoneAndQuantity, Boolean> availableProducts = new HashMap<>();
        for (PhoneAndQuantity product : order.getProducts()) {
            int quantityInWarehouse = warehouseRepository.findQuantityByPhoneId(product.getPhone().getId());
            if (product.getQuantity() <= quantityInWarehouse) {
                availableProducts.put(product, true);
            } else {
                availableProducts.put(product, false);
            }
        }
        if (availableProducts.containsValue(false))
            isSuccess = false;
        return isSuccess;
    }
}
