package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.models.common.PredicateCreator;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ModificationRepository extends GeneralEntityWithNameRepository<Modification> {
    public ModificationRepository() {
        super(Modification.class);
    }

    public List<Modification> getByModel(int modelId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate modelIdEq = PredicateCreator.intIsEqual(root.get("chassis").get("generation").get("model"), modelId, cb);
        CriteriaQuery<Modification> byModelId = cq.where(modelIdEq);
        TypedQuery<Modification> query = entityManager.createQuery(byModelId);
        return query.getResultList();
    }

    public List<Modification> getByVendor(int vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = PredicateCreator
                .intIsEqual(root.get("chassis").get("generation").get("model").get("vendor"), vendorId, cb);
        CriteriaQuery<Modification> byIdAndName = cq.where(idEquals);
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = PredicateCreator
                .intIsEqual(root.get("chassis").get("generation").get("model").get("vendor"), vendorId, cb);
        Predicate nameIsLike = PredicateCreator.stringIsLike(root.get("name"), nameBeginning, cb);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, nameIsLike));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = PredicateCreator
                .intIsEqual(root.get("chassis").get("generation").get("model").get("vendor"), vendorId, cb);
        Predicate nameIsLike = PredicateCreator.stringIsEqual(root.get("name"), carsModelName, cb);
        Predicate yearBetweenStartAndEnd = PredicateCreator
                .yearBetweenStartAndEnd(root.get("start"), root.get("end"), year, cb);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, nameIsLike, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByChassisAndYear(int chassisId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = PredicateCreator
                .intIsEqual(root.get("chassis"), chassisId, cb);
        Predicate yearBetweenStartAndEnd = PredicateCreator
                .yearBetweenStartAndEnd(root.get("start"), root.get("end"), year, cb);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByGenerationAndYear(int generationId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = PredicateCreator
                .intIsEqual(root.get("chassis").get("generation"), generationId, cb);
        Predicate yearBetweenStartAndEnd = PredicateCreator
                .yearBetweenStartAndEnd(root.get("start"), root.get("end"), year, cb);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByModelAndYear(int modelId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate modelIdEq = PredicateCreator
                .intIsEqual(root.get("chassis").get("generation").get("model"), modelId, cb);
        Predicate yearBetweenStartAndEnd = PredicateCreator
                .yearBetweenStartAndEnd(root.get("start"), root.get("end"), year, cb);
        CriteriaQuery<Modification> generationsByModelId = cq.where(cb.and(modelIdEq, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(generationsByModelId);
        return query.getResultList();
    }
}
