package com.thirdwheel.carbase.dao.repositories;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
public class GeneralRepository<T> {
    final protected Class<T> tClass;
    protected EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public T getById(int id) {
        return entityManager.find(tClass, id);
    }
}
