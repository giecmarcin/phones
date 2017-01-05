package com.mmgiec.app.repositories;


import com.mmgiec.app.entities.PhoneAndQuantityInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PhoneAndQuantityInOrderRepository extends JpaRepository<PhoneAndQuantityInOrder, Serializable> {
}
