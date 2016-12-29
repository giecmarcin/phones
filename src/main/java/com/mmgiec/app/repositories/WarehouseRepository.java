package com.mmgiec.app.repositories;

import com.mmgiec.app.entities.Warehouse;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class WarehouseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Warehouse warehouse) {
        if (isExist(warehouse.getPhoneId()) == false && warehouse.getPhoneId() != 0)
            entityManager.persist(warehouse);
    }

    public boolean isExist(int phoneId) {
        TypedQuery<Integer> query = entityManager.createQuery("select w.phoneId from Warehouse w where w.phoneId=:phoneId", Integer.class);
        query.setParameter("phoneId", phoneId);
        List result = query.getResultList();
        if (result.isEmpty())
            return false;
        return true;
    }

    public Integer findQuantityByPhoneId(int phoneId) {
        TypedQuery<Integer> query = entityManager.createQuery("select w.quantity from Warehouse w where w.phoneId=:phoneId", Integer.class);
        query.setParameter("phoneId", phoneId);
        List<Integer> result = query.getResultList();
        if (!result.isEmpty())
            return result.get(0);
        return 0;
    }

    public Warehouse findByPhoneId(int phoneId) {
        TypedQuery<Warehouse> query = entityManager.createQuery("select w from Warehouse w where w.phoneId=:phoneId", Warehouse.class);
        query.setParameter("phoneId", phoneId);
        List<Warehouse> result = query.getResultList();
        if (!result.isEmpty())
            return result.get(0);
        return null;
    }

    public void removeByPhoneId(int phoneId) {
        Warehouse warehouse = findByPhoneId(phoneId);
        if (warehouse != null) {
            entityManager.remove(warehouse);
        }
    }
}
