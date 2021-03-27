package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Vendor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class VendorRepository {
    private EntityManager entityManager;
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveVendor(Vendor huy) {
        entityManager.persist(huy);
        entityManager.flush();
    }

    public Vendor getById(int id) {
        return entityManager.find(Vendor.class, id);
    }
}
