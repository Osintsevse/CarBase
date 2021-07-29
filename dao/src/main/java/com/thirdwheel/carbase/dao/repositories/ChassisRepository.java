package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.models.Vendor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class ChassisRepository extends GeneralEntityRepository<Chassis>
        implements RepositoryWithGettingByVendor<Chassis> {
    public ChassisRepository() {
        super(Chassis.class);
    }

    public List<Chassis> getByVendor(int vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        Predicate vendorIdEquals = getPredicateChassisByVendor(vendorId, cb, root);
        CriteriaQuery<Chassis> cqm = cq.where(vendorIdEquals);
        cq.orderBy(cb.asc(root.get(Chassis.Fields.name)));
        TypedQuery<Chassis> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    @Override
    public List<Chassis> getByVendorAndNameSubstring(Integer vendorId, String nameSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        Predicate vendorIdEquals = getPredicateChassisByVendor(vendorId, cb, root);
        Predicate namePredicate = predicateCreator.stringStartsWithOrHasSubstring(root.get(Chassis.Fields.name), nameSubstring);
        CriteriaQuery<Chassis> cqm = cq.where(cb.and(vendorIdEquals, namePredicate));
        cq.orderBy(cb.asc(root.get(Chassis.Fields.name)));
        TypedQuery<Chassis> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    @Override
    public List<Chassis> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> tupleQuery = cb.createTupleQuery();
        Subquery<Integer> subquery = tupleQuery.subquery(Integer.class);
        Root<Chassis> tupleRoot = subquery.from(tClass);

        Predicate vendorIdEquals = getPredicateChassisByVendor(vendorId, cb, tupleRoot);
        Predicate namePredicate = predicateCreator
                .stringStartsWithOrHasSubstring(tupleRoot.get(Chassis.Fields.name), nameSubstring);

        subquery.select(cb.min(tupleRoot.get(Chassis.Fields.id)));
        subquery.groupBy(tupleRoot.get(Chassis.Fields.name));
        subquery.distinct(true);
        subquery.where(cb.and(vendorIdEquals, namePredicate));

        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);
        cq.where(root.get(Chassis.Fields.id).in(subquery));
        cq.orderBy(cb.asc(root.get(Chassis.Fields.name)));

        TypedQuery<Chassis> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Deprecated
    @Override
    public List<Chassis> getByVendorAndCarsModelAndYear(Integer vendorId, String carsModelName, String year) {
        return getByVendorAndCarsModel(vendorId, carsModelName);
    }

    @Deprecated
    public List<Chassis> getByVendorAndCarsModel(int vendorId, String carsModelName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Chassis> cq = cb.createQuery(tClass);
        Root<Chassis> root = cq.from(tClass);

        Predicate vendorIdEquals = getPredicateChassisByVendor(vendorId, cb, root);
        Predicate namePredicate = cb.equal(root.get(Chassis.Fields.name), carsModelName);

        cq.where(cb.and(vendorIdEquals, namePredicate));
        cq.orderBy(cb.asc(root.get(Chassis.Fields.name)));

        TypedQuery<Chassis> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    private Predicate getPredicateChassisByVendor(int vendorId, CriteriaBuilder cb, Root<Chassis> root) {
        return cb.equal(root.get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId);
    }

}
