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
public class ModelRepository extends GeneralRepository<Model> {
    public ModelRepository() {
        super(Model.class);
    }

    public List<Model> getByVendor(Integer vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> modelRoot = cq.from(tClass);
        CriteriaQuery<Model> modelsByVendorId = cq.where(cb.equal(modelRoot.get("vendor"), vendorId));
        TypedQuery<Model> query = entityManager.createQuery(modelsByVendorId);
        return query.getResultList();
    }

    public List<Model> getByVendor(Integer vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> modelRoot = cq.from(tClass);
        Predicate nameIsLike = cb.like(cb.upper(modelRoot.get("name")), nameBeginning.toUpperCase() + "%");
        Predicate vendorIdEquals = cb.equal(modelRoot.get("vendor"), vendorId);
        CriteriaQuery<Model> cqm = cq.where(cb.and(vendorIdEquals, nameIsLike));
        TypedQuery<Model> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }
}
