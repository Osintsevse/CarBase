package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.EntityWithId;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
public class GeneralEntityWithIdRepository<T extends EntityWithId> implements EntityWithIdRepository<T> {
    final protected Class<T> tClass;
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T getById(int id) {
        return entityManager.find(tClass, id);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(tClass);
        Root<T> rootEntry = cq.from(tClass);

        cq.select(rootEntry);

        TypedQuery<T> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
