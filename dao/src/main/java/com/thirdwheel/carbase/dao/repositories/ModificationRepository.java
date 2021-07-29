package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.*;
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
public class ModificationRepository extends GeneralEntityRepository<Modification> {
    private final SimilarityTagFiltersService similarityTagFiltersService;

    public ModificationRepository(SimilarityTagFiltersService similarityTagFiltersService) {
        super(Modification.class);
        this.similarityTagFiltersService = similarityTagFiltersService;
    }

    public List<Modification> getByModel(int modelId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate modelIdEq = getPredicateModificationByModel(modelId, cb, root);
        CriteriaQuery<Modification> byModelId = cq.where(modelIdEq);
        TypedQuery<Modification> query = entityManager.createQuery(byModelId);
        return query.getResultList();
    }

    public List<Modification> getByModelAndYear(int modelId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate modelIdEq = getPredicateModificationByModel(modelId, cb, root);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);
        CriteriaQuery<Modification> generationsByModelId = cq.where(cb.and(modelIdEq, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(generationsByModelId);
        return query.getResultList();
    }

    public List<Modification> getByVendor(int vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = getPredicateModificationByVendor(vendorId, cb, root);
        CriteriaQuery<Modification> byIdAndName = cq.where(idEquals);
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByVendorAndNameSubstring(int vendorId, String nameSubstring) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate namePredicate = predicateCreator.stringStartsWithOrHasSubstring(root.get(Modification.Fields.name), nameSubstring);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, namePredicate));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByVendorAndNameSubstringDistinctByName(int vendorId, String nameSubstring) {
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

    public List<Modification> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = getPredicateModificationByVendor(vendorId, cb, root);
        Predicate namePredicate = cb.equal(root.get(Modification.Fields.name), carsModelName);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, namePredicate, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByChassisAndYear(int chassisId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.id), chassisId);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByGenerationAndYear(int generationId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.id), generationId);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getSimilar(Modification modification, List<SimilarityTag> tagList) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> tupleQuery = cb.createTupleQuery();
        Subquery<Integer> subquery = tupleQuery.subquery(Integer.class);
        Root<Modification> tupleRoot = subquery.from(tClass);


        List<SimilarityPredicateAndGroupElement> predicateAndGroupElements = tagList.stream()
                .map(x -> similarityTagFiltersService.getTagFilterMap().get(x).getPredicate(modification, tupleRoot))
                .filter(Objects::nonNull).collect(Collectors.toList());

        List<Predicate> predicates = predicateAndGroupElements.stream()
                .map(SimilarityPredicateAndGroupElement::getPredicate)
                .filter(Objects::nonNull).collect(Collectors.toList());

        predicates.add(cb.notEqual(tupleRoot.get(Modification.Fields.chassis), modification.getChassis()));
        List<Expression<?>> groupElements = predicateAndGroupElements.stream()
                .map(SimilarityPredicateAndGroupElement::getGroupElement)
                .filter(Objects::nonNull).collect(Collectors.toList());

        groupElements.add(tupleRoot.get(Modification.Fields.chassis).get(Chassis.Fields.id));
        subquery.select(cb.min(tupleRoot.get(Modification.Fields.id)));
        subquery.groupBy(groupElements);
        subquery.distinct(true);
        subquery.where(cb.and(predicates.toArray(new Predicate[]{})));
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        cq.where(root.get(Modification.Fields.id).in(subquery));

        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.generation).fetch(Generation.Fields.model).fetch(Model.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.rearSuspension);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.frontSuspension);
        root.fetch(Modification.Fields.engineModification).fetch(EngineModification.Fields.engine).fetch(Engine.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    private Predicate getPredicateModificationByModel(int modelId, CriteriaBuilder cb, Root<Modification> root) {
        return cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.id), modelId);
    }

    private Predicate getPredicateModificationByVendor(int vendorId, CriteriaBuilder cb, Root<Modification> root) {
        return cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId);
    }
}
