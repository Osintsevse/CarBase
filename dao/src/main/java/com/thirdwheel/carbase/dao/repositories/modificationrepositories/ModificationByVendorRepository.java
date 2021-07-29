package com.thirdwheel.carbase.dao.repositories.modificationrepositories;

import com.thirdwheel.carbase.dao.models.*;
import com.thirdwheel.carbase.dao.repositories.GeneralEntityWithIdRepository;
import com.thirdwheel.carbase.dao.repositories.RepositoryWithGettingByVendor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class ModificationByVendorRepository extends GeneralEntityWithIdRepository<Modification>
        implements RepositoryWithGettingByVendor<Modification> {

    public ModificationByVendorRepository() {
        super(Modification.class);
    }

    public List<Modification> getByVendor(int vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate idEquals = getPredicateModificationByVendor(vendorId, cb, root);

        cq.where(idEquals);
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Modification> getByVendorAndNameSubstring(Integer vendorId, String nameSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);

        Root<Modification> root = cq.from(tClass);

        Predicate idEquals = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate namePredicate = predicateCreator.stringStartsWithOrHasSubstring(root.get(Modification.Fields.name), nameSubstring);

        cq.where(cb.and(idEquals, namePredicate));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Modification> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> tupleQuery = cb.createTupleQuery();
        Subquery<Integer> subquery = tupleQuery.subquery(Integer.class);
        Root<Modification> tupleRoot = subquery.from(tClass);

        Predicate vendorIdPredicate = getPredicateModificationByVendor(vendorId, cb, tupleRoot);
        Predicate namePredicate = predicateCreator.stringStartsWithOrHasSubstring(tupleRoot.get(Modification.Fields.name), nameSubstring);

        subquery.select(cb.min(tupleRoot.get(Modification.Fields.id)));
        subquery.groupBy(tupleRoot.get(Modification.Fields.name));
        subquery.distinct(true);
        subquery.where(cb.and(vendorIdPredicate, namePredicate));

        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        cq.where(root.get(Modification.Fields.id).in(subquery));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Modification> getByVendorAndNameAndYear(Integer vendorId, String name, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate idEquals = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate namePredicate = cb.equal(root.get(Modification.Fields.name), name);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);

        cq.where(cb.and(idEquals, namePredicate, yearBetweenStartAndEnd));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Modification> getByVendorAndName(Integer vendorId, String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate namePredicate = cb.equal(root.get(Modification.Fields.name), name);

        cq.where(cb.and(vendorPredicate, namePredicate));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Modification> getByVendorIdAndChassisNameAndYear(Integer vendorId, String chassisName, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);
        Predicate chassisPredicate = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.name), chassisName);

        cq.where(cb.and(chassisPredicate, yearBetweenStartAndEnd, vendorPredicate));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Modification> getByVendorIdAndChassisName(Integer vendorId, String chassisName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate chassisPredicate = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.name), chassisName);

        cq.where(cb.and(chassisPredicate, vendorPredicate));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Modification> getByVendorIdAndGenerationNameAndYear(Integer vendorId, String generationName, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);
        Predicate chassisPredicate = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.name), generationName);

        cq.where(cb.and(chassisPredicate, yearBetweenStartAndEnd, vendorPredicate));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Modification> getByVendorIdAndGenerationName(Integer vendorId, String generationName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate chassisPredicate = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.name), generationName);

        cq.where(cb.and(chassisPredicate, vendorPredicate));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Modification> getByVendorIdAndModelNameAndYear(Integer vendorId, String modelName, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);
        Predicate chassisPredicate = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.name), modelName);

        cq.where(cb.and(chassisPredicate, yearBetweenStartAndEnd, vendorPredicate));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Modification> getByVendorIdAndModelName(Integer vendorId, String modelName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate chassisPredicate = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.name), modelName);

        cq.where(cb.and(chassisPredicate, vendorPredicate));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    private Predicate getPredicateModificationByVendor(int vendorId, CriteriaBuilder cb, Root<Modification> root) {
        return cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId);
    }
}
