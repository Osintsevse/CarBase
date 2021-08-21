package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.queries.ModificationQueries;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTagFiltersService;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        ModificationQueries modificationQueryBuilder =
                new ModificationQueries(entityManager).addSubqueryByMinId();

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
                .setPredicate(cb.and(predicates.toArray(new Predicate[]{})));
        modificationQueryBuilder.setFullFetch();

        return modificationQueryBuilder.build().getResultList();
    }

    @Override
    public List<Modification> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        ModificationQueries modificationQueries = new ModificationQueries(entityManager);
        modificationQueries.addSubqueryByMinId().setGroupByNameInSubquery().byVendorId(vendorId)
                .byNameStartsWithOrHasSubstring(nameSubstring);
        return modificationQueries.setFullFetch().build().getResultList();
    }

    public List<Modification> getByVendorAndNameAndYear(Integer vendorId, String name, Integer year) {
        ModificationQueries modificationQueries = new ModificationQueries(entityManager);
        modificationQueries.byYearIsBetween(year).byVendorId(vendorId).byNameIsEqual(name);
        return modificationQueries.setFullFetch().build().getResultList();
    }

    public List<Modification> getByVendorAndName(Integer vendorId, String name) {
        ModificationQueries modificationQueries = new ModificationQueries(entityManager);
        modificationQueries.byVendorId(vendorId).byNameIsEqual(name);
        return modificationQueries.setFullFetch().build().getResultList();
    }

    public List<Modification> getByVendorIdAndChassisNameAndYear(Integer vendorId, String chassisName, Integer year) {
        return new ModificationQueries(entityManager).byChassisNameIsEqual(chassisName).byVendorId(vendorId)
                .byYearIsBetween(year).setFullFetch().build().getResultList();
    }

    public List<Modification> getByVendorIdAndChassisName(Integer vendorId, String chassisName) {
        return new ModificationQueries(entityManager).byChassisNameIsEqual(chassisName).byVendorId(vendorId)
                .setFullFetch().build().getResultList();
    }

    public List<Modification> getByVendorIdAndGenerationNameAndYear(Integer vendorId,
                                                                    String generationName,
                                                                    Integer year) {
        return new ModificationQueries(entityManager).byGenerationNameIsEqual(generationName).byVendorId(vendorId)
                .setFullFetch().byYearIsBetween(year).build().getResultList();
    }

    public List<Modification> getByVendorIdAndGenerationName(Integer vendorId, String generationName) {
        return new ModificationQueries(entityManager).byGenerationNameIsEqual(generationName).byVendorId(vendorId)
                .setFullFetch().build().getResultList();
    }

    public List<Modification> getByVendorIdAndModelNameAndYear(Integer vendorId, String modelName, Integer year) {
        return new ModificationQueries(entityManager).byModelNameIsEqual(modelName).byVendorId(vendorId)
                .setFullFetch().byYearIsBetween(year).build().getResultList();
    }

    public List<Modification> getByVendorIdAndModelName(Integer vendorId, String modelName) {
        return new ModificationQueries(entityManager).byModelNameIsEqual(modelName).byVendorId(vendorId)
                .setFullFetch().build().getResultList();
    }

    @Override
    public Modification getById(int id) {
        ModificationQueries modificationQueries = new ModificationQueries(entityManager);
        modificationQueries.addPredicateByAnd(
                modificationQueries.getCriteriaBuilder().equal(
                        modificationQueries.getRoot().get(Modification.Fields.id), id));
        modificationQueries.setFullFetch();
        return modificationQueries.build().getSingleResult();
    }
}
