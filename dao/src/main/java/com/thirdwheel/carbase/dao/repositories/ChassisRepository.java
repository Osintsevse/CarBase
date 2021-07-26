package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Chassis;
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
public class ChassisRepository extends GeneralEntityRepository<Chassis> {
    public ChassisRepository() {
        super(Chassis.class);
    }

    public List<Chassis> getByVendor(int vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        Predicate vendorIdEquals = getPredicateChassisByVedor(vendorId, cb, root);
        CriteriaQuery<Chassis> cqm = cq.where(vendorIdEquals);
        TypedQuery<Chassis> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Chassis> getByVendorAndName(int vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        Predicate vendorIdEquals = getPredicateChassisByVedor(vendorId, cb, root);
        Predicate namePredicate = predicateCreator.stringStartsWith(root.get(Chassis.Fields.name), nameBeginning);
        CriteriaQuery<Chassis> cqm = cq.where(cb.and(vendorIdEquals, namePredicate));
        TypedQuery<Chassis> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Chassis> getByVendorAndCarsModel(int vendorId, String carsModelName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        Predicate vendorIdEquals = getPredicateChassisByVedor(vendorId, cb, root);
        Predicate namePredicate = cb.equal(root.get(Chassis.Fields.name), carsModelName);
        CriteriaQuery<Chassis> cqm = cq.where(cb.and(vendorIdEquals, namePredicate));
        TypedQuery<Chassis> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    private Predicate getPredicateChassisByVedor(int vendorId, CriteriaBuilder cb, Root<Chassis> root) {
        return cb.equal(root.get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId);
    }

}
