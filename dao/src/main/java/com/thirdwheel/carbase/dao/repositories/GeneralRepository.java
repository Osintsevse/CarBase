package com.thirdwheel.carbase.dao.repositories;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<T> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> rootEntry = cq.from(tClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> query = entityManager.createQuery(all);
        return query.getResultList();
    }

    public List<T> getByNameBeginning(String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> rootEntry = cq.from(tClass);
        CriteriaQuery<T> cqByNameBeginning = cq.where(cb.like(cb.upper(rootEntry.get("name")), nameBeginning.toUpperCase() + "%"));
        TypedQuery<T> query = entityManager.createQuery(cqByNameBeginning);
        return query.getResultList();
    }
}
