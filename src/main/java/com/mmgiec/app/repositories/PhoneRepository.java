package com.mmgiec.app.repositories;

import com.mmgiec.app.entities.Phone;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PhoneRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Phone phone) {
        if (isExist(phone.getFullName()) == false)
            entityManager.persist(phone);
    }

    public boolean isExist(String fullName) {
        TypedQuery query = entityManager.createQuery("select p.fullName from Phone p where p.fullName=:fullName", String.class);
        query.setParameter("fullName", fullName);
        List result = query.getResultList();
        if (result.isEmpty())
            return false;
        return true;
    }

    public List<Phone> findAll() {
        TypedQuery<Phone> query = entityManager.createQuery("select p from Phone p", Phone.class);
        return query.getResultList();
    }

    public List<Phone> findAll(int pageNumber, int pageSize) {
        TypedQuery<Phone> query = entityManager.createQuery("select p from Phone p", Phone.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<String> findAllBrands() {
        TypedQuery<String> query = entityManager.createQuery("select distinct p.brand from Phone p order by p.brand", String.class);
        return query.getResultList();
    }

    public List<Double> findAllBuiltInMemory() {
        TypedQuery<Double> query = entityManager.createQuery("select distinct p.builtInMemory from Phone p order by p.builtInMemory", Double.class);
        return query.getResultList();
    }

    public List<Double> findAllRamSizes() {
        TypedQuery<Double> query = entityManager.createQuery("select distinct p.ram from Phone p order by p.ram", Double.class);
        return query.getResultList();
    }

    public List<Double> findAllDisplaySizes() {
        TypedQuery<Double> query = entityManager.createQuery("select distinct p.sizeOfDisplay from Phone p order by p.sizeOfDisplay", Double.class);
        return query.getResultList();
    }

    public Phone findOne(int id) {
        TypedQuery<Phone> query = entityManager.createQuery("select p from Phone p left join fetch p.communication where p.id=:id", Phone.class);
        query.setParameter("id", id);
        List<Phone> result = query.getResultList();
        if (!result.isEmpty())
            return result.get(0);
        else
            return null;
    }

    public void remove(int phoneId) {
        Phone p = entityManager.find(Phone.class, phoneId);
        entityManager.remove(p);
    }

    public Phone update(Phone phone) {
        int id = phone.getId();
        Phone p = findOne(id);
        p.setBrand(phone.getBrand());
        p.setFullName(phone.getFullName());
        p.setPrice(phone.getPrice());
        p.setProcessor(phone.getProcessor());
        p.setGraphics(phone.getGraphics());
        p.setRam(phone.getRam());
        p.setBuiltInMemory(phone.getBuiltInMemory());
        p.setTypeOfDisplay(phone.getTypeOfDisplay());
        p.setSizeOfDisplay(phone.getSizeOfDisplay());
        p.setResolutionOfDisplay(phone.getResolutionOfDisplay());
        //p.setCommunication(phone.getCommunication());
        p.setNavigation(phone.getNavigation());
        p.setConnectors(phone.getConnectors());
        p.setCapacityOfBattery(phone.getCapacityOfBattery());
        p.setOperatingSystem(phone.getOperatingSystem());
        p.setFrontCameraMPX(phone.getFrontCameraMPX());
        p.setCameraMPX(phone.getCameraMPX());
        p.setFlashLamp(phone.getFlashLamp());
        p.setThickness(phone.getThickness());
        p.setWidth(phone.getWidth());
        p.setHeight(phone.getHeight());
        p.setWeight(phone.getWeight());
        p.setColour(phone.getColour());
        p.setImageUrl(phone.getImageUrl());
        p.setImageUrlXKom(phone.getImageUrlXKom());
        p.setExtraInfo(phone.getExtraInfo());
        p.setGuarantee(phone.getGuarantee());
        p.setResolutionRecordingVideo(phone.getResolutionRecordingVideo());
        p.setIncludedAccessories(phone.getIncludedAccessories());
        entityManager.flush();
        return p;
    }
}
