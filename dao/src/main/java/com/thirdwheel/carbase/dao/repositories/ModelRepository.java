package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.models.Vendor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class ModelRepository extends GeneralEntityWithIdRepository<Model>
        implements RepositoryWithGettingByVendor<Model> {
    public ModelRepository() {
        super(Model.class);
    }

    @Override
    public List<Model> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> tupleQuery = cb.createTupleQuery();
        Subquery<Integer> subquery = tupleQuery.subquery(Integer.class);
        Root<Model> tupleRoot = subquery.from(tClass);

        Predicate vendorPredicate = getPredicateModelByVendor(vendorId, cb, tupleRoot);
        Predicate namePredicate = predicateCreator.stringStartsWithOrHasSubstring(tupleRoot.get(Model.Fields.name), nameSubstring);

        subquery.select(cb.min(tupleRoot.get(Model.Fields.id)));
        subquery.groupBy(tupleRoot.get(Model.Fields.name));
        subquery.distinct(true);
        subquery.where(cb.and(vendorPredicate, namePredicate));

        CriteriaQuery<Model> cq = cb.createQuery(tClass);
        Root<Model> root = cq.from(tClass);
        cq.where(root.get(Model.Fields.id).in(subquery));
        cq.orderBy(cb.asc(root.get(Model.Fields.name)));

        TypedQuery<Model> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    private Predicate getPredicateModelByVendor(int vendorId, CriteriaBuilder cb, Root<Model> root) {
        return cb.equal(root.get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId);
    }
}
