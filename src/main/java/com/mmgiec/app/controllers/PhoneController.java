package com.mmgiec.app.controllers;

import com.mmgiec.app.entities.Phone;
import com.mmgiec.app.model.PhoneAndQuantity;
import com.mmgiec.app.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping("api/phone/all")
    public ResponseEntity<?> findAll(@RequestParam(name = "pageNumber", required = false) Integer pageNumber, @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        List<Phone> phones = null;
        if (pageNumber == null && pageSize == null) {
            phones = phoneService.findAll();
        } else {
            phones = phoneService.findAll(pageNumber, pageSize);
        }
        if (phones.isEmpty())
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(phones);
    }

    @GetMapping("api/phone/brand/all")
    public ResponseEntity<?> findAllBrands() {
        List<String> allBrands = phoneService.findAllBrands();
        if (allBrands.isEmpty())
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(allBrands);
    }

    @GetMapping("api/phone/builtInMemory/all")
    public ResponseEntity<?> findAllBuiltInMemorySizes() {
        List<Double> allBuiltInMemorySizes = phoneService.findAllBuiltInMemorySizes();
        if (allBuiltInMemorySizes.isEmpty())
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(allBuiltInMemorySizes);
    }

    @GetMapping("api/phone/ram/all")
    public ResponseEntity<?> findAllRamSizes() {
        List<Double> allRamSizes = phoneService.findAllRamSizes();
        if (allRamSizes.isEmpty())
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(allRamSizes);
    }

    @GetMapping("api/phone/displaySizes/all")
    public ResponseEntity<?> findAllDisplaySizes() {
        List<Double> allDisplaySizes = phoneService.findAllDisplaySizes();
        if (allDisplaySizes.isEmpty())
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(allDisplaySizes);
    }

    @GetMapping("api/phone/id/{id}")
    public ResponseEntity<?> findOnePhone(@PathVariable int id) {
        Phone phone = phoneService.findOne(id);
        if (phone == null)
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(phone);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping("api/phone/add")
    public ResponseEntity<?> savePhone(@RequestBody PhoneAndQuantity phoneAndQuantity) {
        phoneService.saveWithQuantity(phoneAndQuantity.getPhone(), phoneAndQuantity.getQuantity());
        if (phoneAndQuantity.getPhone().getId() == 0 && phoneAndQuantity.getQuantity() == 0) {
            return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
        }
        return ResponseEntity.ok(phoneAndQuantity.getPhone());
    }

    @GetMapping("api/phone/quantity/{id}")
    public ResponseEntity<?> findAllBrands(@PathVariable int id) {
        Integer quantity = phoneService.findQuantity(id);
        if (quantity == 0)
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(quantity);
    }

    @GetMapping("api/phone/all/quantity")
    public ResponseEntity<?> findAll() {
        List<PhoneAndQuantity> phones = phoneService.findAllWithQuantity();
        if (phones.isEmpty())
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(phones);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "api/phone/remove/{id}")
    public ResponseEntity<?> removePhone(@PathVariable int id) {
        phoneService.removeWithWarehouse(id);
        Phone phone = phoneService.findOne(id);
        if (phone == null) {
            return ResponseEntity.ok(phone);
        }
        return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(value = "api/phone/edit")
    public void editPhone(@RequestBody PhoneAndQuantity phoneAndQuantity) {
        //System.out.println(phoneAndQuantity.getPhone().getCommunication().get(0).getName());
        phoneService.updateWithQuantity(phoneAndQuantity.getPhone(), phoneAndQuantity.getQuantity());
    }
}
