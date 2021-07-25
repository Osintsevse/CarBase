package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.models.Vendor;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class GenerationRepository extends GeneralEntityRepository<Generation> {
    public GenerationRepository() {
        super(Generation.class);
    }

    public List<Generation> getByVendor(Integer vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> root = cq.from(tClass);
        Predicate vendorIdEquals = predicateCreator
                .intIsEqual(root.get(Generation.Fields.model)
                        .get(Model.Fields.vendor)
                        .get(Vendor.Fields.id), vendorId);
        CriteriaQuery<Generation> cqm = cq.where(vendorIdEquals);
        TypedQuery<Generation> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Generation> getByVendorAndNameBeginning(Integer vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> root = cq.from(tClass);
        Predicate vendorIdEquals = predicateCreator
                .intIsEqual(root.get(Generation.Fields.model)
                        .get(Model.Fields.vendor)
                        .get(Vendor.Fields.id), vendorId);
        Predicate nameIsLike = predicateCreator.stringStartsWith(root.get(Generation.Fields.name), nameBeginning);
        CriteriaQuery<Generation> cqm = cq.where(cb.and(vendorIdEquals, nameIsLike));
        TypedQuery<Generation> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Generation> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> root = cq.from(tClass);
        Predicate vendorIdEquals = predicateCreator
                .intIsEqual(root.get(Generation.Fields.model)
                        .get(Model.Fields.vendor)
                        .get(Vendor.Fields.id), vendorId);
        Predicate nameIsEq = predicateCreator.stringIsEqual(root.get(Generation.Fields.name), carsModelName);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Generation.Fields.start), root.get(Generation.Fields.end), year);
        CriteriaQuery<Generation> cqm = cq.where(cb.and(vendorIdEquals, nameIsEq, yearBetweenStartAndEnd));
        TypedQuery<Generation> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }
}
