package com.mmgiec.app.services.impl;

import com.mmgiec.app.entities.Phone;
import com.mmgiec.app.entities.Warehouse;
import com.mmgiec.app.model.PhoneAndQuantity;
import com.mmgiec.app.repositories.PhoneRepository;
import com.mmgiec.app.repositories.WarehouseRepository;
import com.mmgiec.app.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public void save(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    public void saveWithQuantity(Phone phone, int quantity) {
        phoneRepository.save(phone);
        Warehouse warehouse = new Warehouse(phone.getId(), quantity);
        warehouseRepository.save(warehouse);
    }

    @Override
    public boolean isExist(String fullName) {
        return phoneRepository.isExist(fullName);
    }

    @Override
    public List<Phone> findAll(int pageNumber, int pageSize) {
        return phoneRepository.findAll(pageNumber, pageSize);
    }

    @Override
    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    @Override
    public List<String> findAllBrands() {
        return phoneRepository.findAllBrands();
    }

    @Override
    public List<Double> findAllBuiltInMemorySizes() {
        return phoneRepository.findAllBuiltInMemory();
    }

    @Override
    public List<Double> findAllDisplaySizes() {
        return phoneRepository.findAllDisplaySizes();
    }

    @Override
    public List<Double> findAllRamSizes() {
        return phoneRepository.findAllRamSizes();
    }

    @Override
    public Phone findOne(int id) {
        return phoneRepository.findOne(id);
    }

    @Override
    public List<PhoneAndQuantity> findAllWithQuantity() {
        List<Phone> phones = phoneRepository.findAll();
        List<PhoneAndQuantity> phoneAndQuantities = new ArrayList<>();
        for (Phone p : phones) {
            PhoneAndQuantity phoneAndQuantity = new PhoneAndQuantity();
            phoneAndQuantity.setPhone(p);
            phoneAndQuantity.setQuantity(warehouseRepository.findQuantityByPhoneId(p.getId()));
            phoneAndQuantities.add(phoneAndQuantity);
        }
        return phoneAndQuantities;
    }

    @Override
    public Integer findQuantity(int phoneId) {
        return warehouseRepository.findQuantityByPhoneId(phoneId);
    }
}
