package com.mmgiec.app.services.impl;

import com.mmgiec.app.entities.HistoryOfOrders;
import com.mmgiec.app.entities.User;
import com.mmgiec.app.repositories.HistoryOfOrdersRepository;
import com.mmgiec.app.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryOfOrdersRepository historyOfOrdersRepository;


    @Override
    public List<HistoryOfOrders> findByUser(User user) {
        return historyOfOrdersRepository.findByUser(user);
    }
}
