package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.IEntityWithName;
import com.thirdwheel.carbase.dao.models.common.PredicateCreator;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
        Predicate nameIsLike = PredicateCreator.stringIsLike(rootEntry.get("name"), nameBeginning, cb);
        CriteriaQuery<T> cqByNameBeginning = cq.where(nameIsLike);
        TypedQuery<T> query = entityManager.createQuery(cqByNameBeginning);
        return query.getResultList();
    }
}
