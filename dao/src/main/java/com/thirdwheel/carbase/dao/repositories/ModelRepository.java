package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.models.Vendor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class ModelRepository extends GeneralEntityRepository<Model>
        implements RepositoryWithGettingByVendor<Model> {
    public ModelRepository() {
        super(Model.class);
    }

    public List<Model> getByVendor(Integer vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);

        Predicate vendorIdEq = getPredicateModelByVendor(vendorId, cb, root);

        cq.where(vendorIdEq);
        cq.orderBy(cb.asc(root.get(Model.Fields.name)));

        TypedQuery<Model> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Model> getByVendorAndNameSubstring(Integer vendorId, String nameSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);

        Predicate vendorIdEq = getPredicateModelByVendor(vendorId, cb, root);
        Predicate namePredicate = predicateCreator.stringStartsWithOrHasSubstring(root.get(Model.Fields.name), nameSubstring);

        cq.where(cb.and(vendorIdEq, namePredicate));
        cq.orderBy(cb.asc(root.get(Model.Fields.name)));

        TypedQuery<Model> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Model> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> tupleQuery = cb.createTupleQuery();
        Subquery<Integer> subquery = tupleQuery.subquery(Integer.class);
        Root<Model> tupleRoot = subquery.from(tClass);

        Predicate vendorIdPredicate = getPredicateModelByVendor(vendorId, cb, tupleRoot);
        Predicate namePredicate = predicateCreator.stringStartsWithOrHasSubstring(tupleRoot.get(Model.Fields.name), nameSubstring);

        subquery.select(cb.min(tupleRoot.get(Model.Fields.id)));
        subquery.groupBy(tupleRoot.get(Model.Fields.name));
        subquery.distinct(true);
        subquery.where(cb.and(vendorIdPredicate, namePredicate));

        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);
        cq.where(root.get(Model.Fields.id).in(subquery));
        cq.orderBy(cb.asc(root.get(Model.Fields.name)));

        TypedQuery<Model> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    @Deprecated
    public List<Model> getByVendorAndCarsModelAndYear(Integer vendorId, String carsModelName, String year) {
        return getByVendorAndCarsModel(vendorId, carsModelName);
    }

    @Deprecated
    public List<Model> getByVendorAndCarsModel(Integer vendorId, String carsModelName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);

        Predicate vendorIdEq = getPredicateModelByVendor(vendorId, cb, root);
        Predicate namePredicate = cb.equal(root.get(Model.Fields.name), carsModelName);

        cq.where(cb.and(vendorIdEq, namePredicate));
        cq.orderBy(cb.asc(root.get(Model.Fields.name)));

        TypedQuery<Model> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    private Predicate getPredicateModelByVendor(int vendorId, CriteriaBuilder cb, Root<Model> root) {
        return cb.equal(root.get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId);
    }
}
