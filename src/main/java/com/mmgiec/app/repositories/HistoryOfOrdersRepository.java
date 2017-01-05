package com.mmgiec.app.repositories;

import com.mmgiec.app.entities.HistoryOfOrders;
import com.mmgiec.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;


public interface HistoryOfOrdersRepository extends JpaRepository<HistoryOfOrders, Serializable> {
    List<HistoryOfOrders> findByUser(User u);
}
