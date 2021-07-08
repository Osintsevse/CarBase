package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Generation;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;

@Service
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

    public List<Generation> getByVendor(Integer vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> root = cq.from(tClass);
        Predicate nameIsLike = cb.like(cb.upper(root.get("name")), nameBeginning.toUpperCase() + "%");
        Predicate vendorIdEquals = cb.equal(root.get("model").get("vendor"), vendorId);
        CriteriaQuery<Generation> cqm = cq.where(cb.and(vendorIdEquals, nameIsLike));
        TypedQuery<Generation> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Generation> getByVendor(Integer vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> root = cq.from(tClass);
        Predicate vendorIdEquals = cb.equal(root.get("model").get("vendor"), vendorId);
        CriteriaQuery<Generation> cqm = cq.where(vendorIdEquals);
        TypedQuery<Generation> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }
}
