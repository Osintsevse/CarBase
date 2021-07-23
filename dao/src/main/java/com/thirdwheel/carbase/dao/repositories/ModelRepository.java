package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Model;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ModelRepository extends GeneralEntityWithNameRepository<Model> {
    public ModelRepository() {
        super(Model.class);
    }

    public List<Model> getByVendor(Integer vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);
        Predicate vendorIdEq = predicateCreator.intIsEqual(root.get("vendor"), vendorId);
        CriteriaQuery<Model> modelsByVendorId = cq.where(vendorIdEq);
        TypedQuery<Model> query = entityManager.createQuery(modelsByVendorId);
        return query.getResultList();
    }

    public List<Model> getByVendorAndNameBeginning(Integer vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);
        Predicate vendorIdEq = predicateCreator.intIsEqual(root.get("vendor"), vendorId);
        Predicate nameIsLike = predicateCreator.stringStartsWith(root.get("name"), nameBeginning);
        CriteriaQuery<Model> cqm = cq.where(cb.and(vendorIdEq, nameIsLike));
        TypedQuery<Model> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Model> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);
        Predicate vendorIdEq = predicateCreator.intIsEqual(root.get("vendor"), vendorId);
        Predicate nameIsEq = predicateCreator.stringIsEqual(root.get("name"), carsModelName);
        CriteriaQuery<Model> cqm = cq.where(cb.and(vendorIdEq, nameIsEq));
        TypedQuery<Model> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }
}
