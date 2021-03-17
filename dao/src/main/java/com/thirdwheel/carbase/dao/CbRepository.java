package com.thirdwheel.carbase.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CbRepository {
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}