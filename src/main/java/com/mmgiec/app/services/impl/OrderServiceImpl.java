package com.mmgiec.app.services.impl;

import com.itextpdf.text.DocumentException;
import com.mmgiec.app.entities.HistoryOfOrders;
import com.mmgiec.app.entities.PhoneAndQuantityInOrder;
import com.mmgiec.app.entities.User;
import com.mmgiec.app.model.Order;
import com.mmgiec.app.model.PhoneAndQuantity;
import com.mmgiec.app.repositories.HistoryOfOrdersRepository;
import com.mmgiec.app.repositories.PhoneAndQuantityInOrderRepository;
import com.mmgiec.app.repositories.WarehouseRepository;
import com.mmgiec.app.services.OrderService;
import com.mmgiec.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private HistoryOfOrdersRepository historyOfOrdersRepository;

    @Autowired
    private PhoneAndQuantityInOrderRepository phoneAndQuantityInOrderRepository;

    @Autowired
    private UserService userService;

    @Transactional
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
        else {
            List<PhoneAndQuantityInOrder> phonesInOrder = new ArrayList();
            for (Map.Entry<PhoneAndQuantity, Boolean> entry : availableProducts.entrySet()) {
                PhoneAndQuantity key = entry.getKey();
                Boolean value = entry.getValue();
                PhoneAndQuantityInOrder onePhoneInOrder = new PhoneAndQuantityInOrder(key.getPhone(), key.getQuantity());
                phonesInOrder.add(onePhoneInOrder);
            }
            User customer = userService.findByEmail(order.getCustomerEmail()).get();
            HistoryOfOrders historyOfOrders = new HistoryOfOrders(customer, phonesInOrder, LocalDate.now());
            historyOfOrdersRepository.save(historyOfOrders);
            if (historyOfOrders.getId() == 0) {
                isSuccess = false;
            } else {
                isSuccess = true;
                Pdf pdf = new Pdf(phonesInOrder,customer);
                try {
                    pdf.create();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }
}
