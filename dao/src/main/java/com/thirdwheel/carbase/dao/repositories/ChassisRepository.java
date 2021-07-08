package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Chassis;
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

    public List<Chassis> getByVendor(Integer vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        Predicate nameIsLike = cb.like(cb.upper(root.get("name")), nameBeginning.toUpperCase() + "%");
        Predicate vendorIdEquals = cb.equal(root.get("generation").get("model").get("vendor"), vendorId);
        CriteriaQuery<Chassis> cqm = cq.where(cb.and(vendorIdEquals, nameIsLike));
        TypedQuery<Chassis> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Chassis> getByVendor(Integer vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        Predicate vendorIdEquals = cb.equal(root.get("generation").get("model").get("vendor"), vendorId);
        CriteriaQuery<Chassis> cqm = cq.where(vendorIdEquals);
        TypedQuery<Chassis> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }
}
