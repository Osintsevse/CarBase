package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.*;
import com.thirdwheel.carbase.dao.querybuilders.QueryBuilder;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTagFiltersService;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ModificationRepository extends GeneralEntityWithIdRepository<Modification>
        implements RepositoryWithGettingByVendor<Modification> {
    private final SimilarityTagFiltersService similarityTagFiltersService;

    public ModificationRepository(SimilarityTagFiltersService similarityTagFiltersService) {
        super(Modification.class);
        this.similarityTagFiltersService = similarityTagFiltersService;
    }

    public List<Modification> getSimilar(Modification modification, List<SimilarityTag> tagList) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Subquery<Integer> subquery = cq.subquery(Integer.class);
        Root<Modification> subqueryRoot = subquery.from(tClass);


        List<SimilarityPredicateAndGroupElement> predicateAndGroupElements = tagList.stream()
                .map(x -> similarityTagFiltersService.getTagFilterMap().get(x).getPredicate(modification, subqueryRoot))
                .filter(Objects::nonNull).collect(Collectors.toList());

        List<Predicate> predicates = predicateAndGroupElements.stream()
                .map(SimilarityPredicateAndGroupElement::getPredicate)
                .filter(Objects::nonNull).collect(Collectors.toList());
        predicates.add(cb.notEqual(subqueryRoot.get(Modification.Fields.id), modification.getId()));

        List<Expression<?>> groupElements = predicateAndGroupElements.stream()
                .map(SimilarityPredicateAndGroupElement::getGroupElement)
                .filter(Objects::nonNull).collect(Collectors.toList());
        groupElements.add(subqueryRoot.get(Modification.Fields.chassis).get(Chassis.Fields.id));

        subquery.select(cb.min(subqueryRoot.get(Modification.Fields.id)));
        subquery.groupBy(groupElements);
        subquery.distinct(true);

        Predicate and = cb.and(predicates.toArray(new Predicate[]{}));
        subquery.where(and);

        Root<Modification> root = cq.from(tClass);
        cq.where(root.get(Modification.Fields.id).in(subquery));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.generation).fetch(Generation.Fields.model)
                .fetch(Model.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.rearSuspension);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.frontSuspension);
        root.fetch(Modification.Fields.engineModification).fetch(EngineModification.Fields.engine)
                .fetch(Engine.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);

        TypedQuery<Modification> query = entityManager.createQuery(cq);

        QueryBuilder<Modification> modificationQueryBuilder =
                new QueryBuilder<Modification>(entityManager,Modification.class,Modification.Fields.name);
        TypedQuery<Modification> query1 = modificationQueryBuilder.setSubquery(Modification.Fields.id)
                .setSubqueryGroupBy(groupElements)
                .setModificationFetch()
                .setPredicate(cb.and(predicates.toArray(new Predicate[]{})))
                .build();
        return query1.getResultList();
    }

    @Override
    public List<Modification> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> tupleQuery = cb.createTupleQuery();
        Subquery<Integer> subquery = tupleQuery.subquery(Integer.class);
        Root<Modification> subqueryRoot = subquery.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, subqueryRoot);
        Predicate namePredicate = predicateCreator
                .stringStartsWithOrHasSubstring(subqueryRoot.get(Modification.Fields.name), nameSubstring);

        subquery.select(cb.min(subqueryRoot.get(Modification.Fields.id)));
        subquery.groupBy(subqueryRoot.get(Modification.Fields.name));
        subquery.distinct(true);
        subquery.where(cb.and(vendorPredicate, namePredicate));

        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        cq.where(root.get(Modification.Fields.id).in(subquery));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Modification> getByVendorAndNameAndYear(Integer vendorId, String name, Integer year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate namePredicate = cb.equal(root.get(Modification.Fields.name), name);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);

        cq.where(cb.and(vendorPredicate, namePredicate, yearBetweenStartAndEnd));
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

    public List<Modification> getByVendorIdAndChassisNameAndYear(Integer vendorId, String chassisName, Integer year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate yearPredicate = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);
        Predicate chassisPredicate = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.name), chassisName);

        cq.where(cb.and(chassisPredicate, yearPredicate, vendorPredicate));
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

    public List<Modification> getByVendorIdAndGenerationNameAndYear(Integer vendorId, String generationName, Integer year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate yearPredicate = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);
        Predicate chassisPredicate = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.name), generationName);

        cq.where(cb.and(chassisPredicate, yearPredicate, vendorPredicate));
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

    public List<Modification> getByVendorIdAndModelNameAndYear(Integer vendorId, String modelName, Integer year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate vendorPredicate = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate yearPredicate = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);
        Predicate chassisPredicate = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.name), modelName);

        cq.where(cb.and(chassisPredicate, yearPredicate, vendorPredicate));
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
