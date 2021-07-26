package com.thirdwheel.carbase.dao.repositories;

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
public class ModelRepository extends GeneralEntityRepository<Model> {
    public ModelRepository() {
        super(Model.class);
    }

    public List<Model> getByVendor(Integer vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);
        Predicate vendorIdEq = getPredicateModelByVendor(vendorId, cb, root);
        CriteriaQuery<Model> modelsByVendorId = cq.where(vendorIdEq);
        TypedQuery<Model> query = entityManager.createQuery(modelsByVendorId);
        return query.getResultList();
    }

    public List<Model> getByVendorAndNameBeginning(Integer vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);
        Predicate vendorIdEq = getPredicateModelByVendor(vendorId, cb, root);
        Predicate namePredicate = predicateCreator.stringStartsWith(root.get(Model.Fields.name), nameBeginning);
        CriteriaQuery<Model> cqm = cq.where(cb.and(vendorIdEq, namePredicate));
        TypedQuery<Model> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Model> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);
        Predicate vendorIdEq = getPredicateModelByVendor(vendorId, cb, root);
        Predicate namePredicate = cb.equal(root.get(Model.Fields.name), carsModelName);
        CriteriaQuery<Model> cqm = cq.where(cb.and(vendorIdEq, namePredicate));
        TypedQuery<Model> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    private Predicate getPredicateModelByVendor(int vendorId, CriteriaBuilder cb, Root<Model> root) {
        return cb.equal(root.get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId);
    }
}
