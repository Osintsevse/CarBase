package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Generation;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;

public class GenerationRepository extends GeneralEntityWithNameRepository<Generation> {
    public GenerationRepository() {
        super(Generation.class);
    }

    public List<Generation> getByModel(Integer modelId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> modelRoot = cq.from(tClass);
        CriteriaQuery<Generation> generationsByModelId = cq.where(cb.equal(modelRoot.get("model"), modelId));
        TypedQuery<Generation> query = entityManager.createQuery(generationsByModelId);
        return query.getResultList();
    }

    public List<Generation> getByModel(Integer modelId, String year) {
        LocalDate beginningOfYear = LocalDate.parse(year + "-01-01");
        LocalDate endOfYear = LocalDate.parse(year + "-12-31");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> modelRoot = cq.from(tClass);
        Predicate modelIdEquals = cb.equal(modelRoot.get("model"), modelId);
        Path<LocalDate> start = modelRoot.get("start");
        Predicate startPredicate = cb.or(
                cb.lessThanOrEqualTo(start, cb.literal(endOfYear)),
                cb.isNull(start));
        Path<LocalDate> end = modelRoot.get("end");
        Predicate endPredicate = cb.or(
                cb.greaterThanOrEqualTo(end, beginningOfYear),
                cb.isNull(end));
        CriteriaQuery<Generation> generationsByModelId = cq.where(cb.and(modelIdEquals, startPredicate, endPredicate));
        TypedQuery<Generation> query = entityManager.createQuery(generationsByModelId);
        return query.getResultList();
    }
}
