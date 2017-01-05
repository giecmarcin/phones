package com.mmgiec.app.repositories;

import com.mmgiec.app.entities.HistoryOfOrders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


public interface HistoryOfOrdersRepository extends JpaRepository<HistoryOfOrders, Serializable> {
}
