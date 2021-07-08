package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Modification;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;

@Service
public class ModificationRepository extends GeneralEntityWithNameRepository<Modification> {
    public ModificationRepository() {
        super(Modification.class);
    }

    public List<Modification> getByModel(Integer modelId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> modelRoot = cq.from(tClass);
        CriteriaQuery<Modification> byModelId = cq.where(
                cb.equal(modelRoot.get("chassis").get("generation").get("model"), modelId));
        TypedQuery<Modification> query = entityManager.createQuery(byModelId);
        return query.getResultList();
    }

    public List<Modification> getByModel(Integer modelId, String year) {
        LocalDate beginningOfYear = LocalDate.parse(year + "-01-01");
        LocalDate endOfYear = LocalDate.parse(year + "-12-31");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> modelRoot = cq.from(tClass);
        Predicate modelIdEquals = cb.equal(modelRoot.get("chassis").get("generation").get("model"), modelId);
        Path<LocalDate> start = modelRoot.get("start");
        Predicate startPredicate = cb.or(
                cb.lessThanOrEqualTo(start, endOfYear),
                cb.isNull(start));
        Path<LocalDate> end = modelRoot.get("end");
        Predicate endPredicate = cb.or(
                cb.greaterThanOrEqualTo(end, beginningOfYear),
                cb.isNull(end));
        CriteriaQuery<Modification> generationsByModelId = cq.where(cb.and(modelIdEquals, startPredicate, endPredicate));
        TypedQuery<Modification> query = entityManager.createQuery(generationsByModelId);
        return query.getResultList();
    }

    public List<Modification> getByVendor(Integer vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> modelRoot = cq.from(tClass);
        Predicate idEquals = cb.equal(modelRoot.get("chassis").get("generation").get("model").get("vendor"), vendorId);
        Predicate nameIsLike = cb.like(cb.upper(modelRoot.get("name")), nameBeginning.toUpperCase() + "%");
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, nameIsLike));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByVendor(Integer vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> modelRoot = cq.from(tClass);
        Predicate idEquals = cb.equal(modelRoot.get("chassis").get("generation").get("model").get("vendor"), vendorId);
        CriteriaQuery<Modification> byIdAndName = cq.where(idEquals);
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }
}
