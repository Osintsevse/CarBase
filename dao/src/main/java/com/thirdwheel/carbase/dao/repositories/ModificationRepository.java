package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.*;
import com.thirdwheel.carbase.dao.querybuilders.AbstractQueries;
import com.thirdwheel.carbase.dao.querybuilders.ModificationQueries;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTagFiltersService;
import org.springframework.stereotype.Service;

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
        AbstractQueries<Modification> modificationQueryBuilder =
                new ModificationQueries(entityManager).setSubqueryByMinId();

        Root<Modification> subqueryRoot = modificationQueryBuilder.getSubqueryRoot();
        CriteriaBuilder cb = modificationQueryBuilder.getCriteriaBuilder();

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

        modificationQueryBuilder.setGroupByInSubquery(groupElements)
                .setPredicate(cb.and(predicates.toArray(new Predicate[]{}))).setFetch();

        return modificationQueryBuilder.build().getResultList();
    }

    @Override
    public List<Modification> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        return new ModificationQueries(entityManager)
                .setSubqueryByMinId().setGroupByNameInSubquery().byVendorId(vendorId)
                .setNameStartsWithOrHasSubstring(nameSubstring).build().getResultList();
    }

    public List<Modification> getByVendorAndNameAndYear(Integer vendorId, String name, Integer year) {
        return new ModificationQueries(entityManager).setYearIsBetween(year)
                .byVendorId(vendorId).setNameIsEqual(name).build().getResultList();
    }

    public List<Modification> getByVendorAndName(Integer vendorId, String name) {
        return new ModificationQueries(entityManager).byVendorId(vendorId).setNameIsEqual(name).build().getResultList();
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
