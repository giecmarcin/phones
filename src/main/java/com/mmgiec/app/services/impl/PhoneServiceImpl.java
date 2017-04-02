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
import java.util.Map;
import java.util.TreeMap;

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

    @Override
    public void removeWithWarehouse(int phoneId) {
        phoneRepository.remove(phoneId);
        warehouseRepository.removeByPhoneId(phoneId);
    }

    @Override
    public void updateWithQuantity(Phone phone, int newQuantity) {
        phoneRepository.update(phone);
        warehouseRepository.updateByPhoneId(phone.getId(), newQuantity);
    }

    public void findSimilar(Phone p1){
        List<Phone> all = findAll();
        Map<Double, Phone> sortedMap = new TreeMap<>();
        for(int i=0; i<all.size();i++){
            Phone p2 = all.get(i);
            double tempResultPrice  = Math.abs(p1.getPrice()- p2.getPrice());
            double tempResultRam = Math.abs(p1.getRam()- p2.getRam());
            double tempResultInMemory = Math.abs(p1.getBuiltInMemory()- p2.getBuiltInMemory());
            double tempResultDisplay = Math.abs(p1.getSizeOfDisplay()- p2.getSizeOfDisplay());
            double sum = tempResultPrice+tempResultRam+tempResultInMemory+tempResultDisplay;
            sortedMap.put(sum,p2);
        }
        System.out.println("Id podobnych: ");
        int i=0;
        for (Map.Entry<Double, Phone> entry : sortedMap.entrySet()) {
//            System.out.println("Key : " + entry.getKey()
//                    + " Value : " + entry.getValue());
            //if(i<3){
                System.out.println("Avg: " + entry.getKey() + " Id: " + entry.getValue().getId());
                //break;
//            }
//            i++;
        }
    }
}
