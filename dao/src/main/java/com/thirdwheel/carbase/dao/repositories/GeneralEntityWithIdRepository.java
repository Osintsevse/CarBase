package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.EntityWithId;
import com.thirdwheel.carbase.dao.models.common.PredicateCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    protected PredicateCreator predicateCreator;
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
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> query = entityManager.createQuery(all);
        return query.getResultList();
    }
}
