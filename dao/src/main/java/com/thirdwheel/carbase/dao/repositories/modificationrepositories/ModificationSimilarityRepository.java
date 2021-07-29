package com.thirdwheel.carbase.dao.repositories.modificationrepositories;

import com.thirdwheel.carbase.dao.models.*;
import com.thirdwheel.carbase.dao.repositories.GeneralEntityWithIdRepository;
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
public class ModificationSimilarityRepository extends GeneralEntityWithIdRepository<Modification> {
    private final SimilarityTagFiltersService similarityTagFiltersService;

    public ModificationSimilarityRepository(SimilarityTagFiltersService similarityTagFiltersService) {
        super(Modification.class);
        this.similarityTagFiltersService = similarityTagFiltersService;
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
        predicates.add(cb.notEqual(tupleRoot.get(Modification.Fields.id), modification.getId()));

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
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.generation).fetch(Generation.Fields.model).fetch(Model.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.rearSuspension);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.frontSuspension);
        root.fetch(Modification.Fields.engineModification).fetch(EngineModification.Fields.engine).fetch(Engine.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
