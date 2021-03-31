package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Vendor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class VendorRepository {
    private EntityManager entityManager;
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveVendor(Vendor vendor) {
        entityManager.persist(vendor);
        entityManager.flush();
    }

    public Vendor getById(int id) {
        return entityManager.find(Vendor.class, id);
    }
}
