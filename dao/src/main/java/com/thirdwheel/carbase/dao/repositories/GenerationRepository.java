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
public class GenerationRepository extends GeneralEntityRepository<Generation> {
    public GenerationRepository() {
        super(Generation.class);
    }

    public List<Generation> getByVendor(Integer vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> root = cq.from(tClass);
        Predicate vendorIdEquals = getPredicateGenerationByVendor(vendorId, cb, root);
        CriteriaQuery<Generation> cqm = cq.where(vendorIdEquals);
        TypedQuery<Generation> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Generation> getByVendorAndNameSubstring(Integer vendorId, String nameSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> root = cq.from(tClass);
        Predicate vendorIdEquals = getPredicateGenerationByVendor(vendorId, cb, root);
        Predicate namePredicate = predicateCreator.stringStartsWithOrHasSubstring(root.get(Generation.Fields.name), nameSubstring);
        CriteriaQuery<Generation> cqm = cq.where(cb.and(vendorIdEquals, namePredicate));
        TypedQuery<Generation> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    public List<Generation> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> tupleQuery = cb.createTupleQuery();
        Subquery<Integer> subquery = tupleQuery.subquery(Integer.class);
        Root<Generation> tupleRoot = subquery.from(tClass);

        Predicate vendorIdPredicate = getPredicateGenerationByVendor(vendorId, cb, tupleRoot);
        Predicate namePredicate = predicateCreator.stringStartsWithOrHasSubstring(tupleRoot.get(Generation.Fields.name), nameBeginning);

        subquery.select(cb.min(tupleRoot.get(Generation.Fields.id)));
        subquery.groupBy(tupleRoot.get(Generation.Fields.name));
        subquery.distinct(true);
        subquery.where(cb.and(vendorIdPredicate, namePredicate));

        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> root = cq.from(tClass);
        cq.where(root.get(Generation.Fields.id).in(subquery));
        cq.orderBy(cb.asc(root.get(Generation.Fields.name)));

        TypedQuery<Generation> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Generation> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Generation> cq = cb.createQuery(tClass);
        Root<Generation> root = cq.from(tClass);
        Predicate vendorIdEquals = getPredicateGenerationByVendor(vendorId, cb, root);
        Predicate namePredicate = cb.equal(root.get(Generation.Fields.name), carsModelName);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Generation.Fields.start), root.get(Generation.Fields.end), year);
        CriteriaQuery<Generation> cqm = cq.where(cb.and(vendorIdEquals, namePredicate, yearBetweenStartAndEnd));
        TypedQuery<Generation> query = entityManager.createQuery(cqm);
        return query.getResultList();
    }

    private Predicate getPredicateGenerationByVendor(Integer vendorId, CriteriaBuilder cb, Root<Generation> root) {
        return cb.equal(root.get(Generation.Fields.model)
                        .get(Model.Fields.vendor)
                        .get(Vendor.Fields.id),
                vendorId);
    }
}
