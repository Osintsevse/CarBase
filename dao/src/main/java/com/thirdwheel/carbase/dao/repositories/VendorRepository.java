package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Vendor;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class VendorRepository extends GeneralEntityRepository<Vendor> {
    public VendorRepository() {
        super(Vendor.class);
    }

    public List<Vendor> getByNameBeginning(String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vendor> cq = cb.createQuery(Vendor.class);

        Root<Vendor> root = cq.from(Vendor.class);
        root.fetch(Vendor.Fields.vendorsConfiguration);

        Predicate namePredicate = predicateCreator.stringStartsWithOrHasSubstring(root.get(Vendor.Fields.name), nameBeginning);
        CriteriaQuery<Vendor> cqByNameBeginning = cq.where(namePredicate);

        TypedQuery<Vendor> query = entityManager.createQuery(cqByNameBeginning);
        return query.getResultList();
    }

    @Override
    public List<Vendor> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vendor> cq = cb.createQuery(tClass);

        Root<Vendor> root = cq.from(tClass);
        root.fetch(Vendor.Fields.vendorsConfiguration);

        CriteriaQuery<Vendor> all = cq.select(root);

        TypedQuery<Vendor> query = entityManager.createQuery(all);
        return query.getResultList();
    }

    @Override
    public Vendor getById(int id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vendor> cq = cb.createQuery(tClass);

        Root<Vendor> root = cq.from(tClass);
        root.fetch(Vendor.Fields.vendorsConfiguration);

        CriteriaQuery<Vendor> vendorByIdCQ = cq.where(cb.equal(root.get(Vendor.Fields.id), id));

        TypedQuery<Vendor> query = entityManager.createQuery(vendorByIdCQ);
        return query.getSingleResult();
    }
}
