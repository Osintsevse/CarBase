package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.common.PredicateCreator;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ChassisRepository extends GeneralEntityWithNameRepository<Chassis> {
    public ChassisRepository() {
        super(Chassis.class);
    }

    public List<Chassis> getByVendor(int vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        Predicate vendorIdEquals = PredicateCreator
                .intIsEqual(root.get("generation").get("model").get("vendor"), vendorId, cb);
        CriteriaQuery<Chassis> cqm = cq.where(vendorIdEquals);
        TypedQuery<Chassis> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Chassis> getByVendorAndName(int vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        Predicate nameIsLike = PredicateCreator.stringIsLike(root.get("name"), nameBeginning, cb);
        Predicate vendorIdEquals = PredicateCreator
                .intIsEqual(root.get("generation").get("model").get("vendor"), vendorId, cb);
        CriteriaQuery<Chassis> cqm = cq.where(cb.and(vendorIdEquals, nameIsLike));
        TypedQuery<Chassis> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Chassis> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        Predicate nameIsEq = PredicateCreator.stringIsEqual(root.get("name"), carsModelName, cb);
        Predicate vendorIdEquals = PredicateCreator
                .intIsEqual(root.get("generation").get("model").get("vendor"), vendorId, cb);
        CriteriaQuery<Chassis> cqm = cq.where(cb.and(vendorIdEquals, nameIsEq));
        TypedQuery<Chassis> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }
}
