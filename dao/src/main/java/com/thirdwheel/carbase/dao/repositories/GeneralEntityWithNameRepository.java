package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.IEntityWithName;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GeneralEntityWithNameRepository<T extends IEntityWithName> extends GeneralEntityRepository<T> {
    public GeneralEntityWithNameRepository(Class<T> tClass) {
        super(tClass);
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
