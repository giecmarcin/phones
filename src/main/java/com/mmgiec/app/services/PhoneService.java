package com.mmgiec.app.services;


import com.mmgiec.app.entities.Phone;
import com.mmgiec.app.model.PhoneAndQuantity;

import java.util.List;

public interface PhoneService {
    void save(Phone phone);

    void saveWithQuantity(Phone phone, int quantity);
    boolean isExist(String fullName);

    List<Phone> findAll(int pageNumber, int pageSize);

    List<Phone> findAll();

    List<String> findAllBrands();

    List<Double> findAllBuiltInMemorySizes();

    List<Double> findAllDisplaySizes();

    List<Double> findAllRamSizes();

    Phone findOne(int id);

    List<PhoneAndQuantity> findAllWithQuantity();
}
