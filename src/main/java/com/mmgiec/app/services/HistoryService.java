package com.mmgiec.app.services;


import com.mmgiec.app.entities.HistoryOfOrders;
import com.mmgiec.app.entities.User;

import java.util.List;

public interface HistoryService {
    List<HistoryOfOrders> findByUser(User user);
}
