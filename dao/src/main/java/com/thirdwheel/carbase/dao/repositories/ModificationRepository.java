package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTagFilterFactory;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ModificationRepository extends GeneralEntityWithNameRepository<Modification> {
    private final SimilarityTagFilterFactory similarityTagFilterFactory;
    private final Map<SimilarityTag, SimilarityTagFilter> similarityTagPredicates = new HashMap<>();

    public ModificationRepository(SimilarityTagFilterFactory similarityTagFilterFactory) {
        super(Modification.class);
        this.similarityTagFilterFactory = similarityTagFilterFactory;
    }

    public List<Modification> getByModel(int modelId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate modelIdEq = predicateCreator.intIsEqual(root.get("chassis").get("generation").get("model"), modelId);
        CriteriaQuery<Modification> byModelId = cq.where(modelIdEq);
        TypedQuery<Modification> query = entityManager.createQuery(byModelId);
        return query.getResultList();
    }

    public List<Modification> getByVendor(int vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = predicateCreator
                .intIsEqual(root.get("chassis").get("generation").get("model").get("vendor"), vendorId);
        CriteriaQuery<Modification> byIdAndName = cq.where(idEquals);
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = predicateCreator
                .intIsEqual(root.get("chassis").get("generation").get("model").get("vendor"), vendorId);
        Predicate nameIsLike = predicateCreator.stringStartsWith(root.get("name"), nameBeginning);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, nameIsLike));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = predicateCreator
                .intIsEqual(root.get("chassis").get("generation").get("model").get("vendor"), vendorId);
        Predicate nameIsLike = predicateCreator.stringIsEqual(root.get("name"), carsModelName);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get("start"), root.get("end"), year);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, nameIsLike, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByChassisAndYear(int chassisId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = predicateCreator
                .intIsEqual(root.get("chassis"), chassisId);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get("start"), root.get("end"), year);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByGenerationAndYear(int generationId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate idEquals = predicateCreator
                .intIsEqual(root.get("chassis").get("generation"), generationId);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get("start"), root.get("end"), year);
        CriteriaQuery<Modification> byIdAndName = cq.where(cb.and(idEquals, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(byIdAndName);
        return query.getResultList();
    }

    public List<Modification> getByModelAndYear(int modelId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);
        Predicate modelIdEq = predicateCreator
                .intIsEqual(root.get("chassis").get("generation").get("model"), modelId);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get("start"), root.get("end"), year);
        CriteriaQuery<Modification> generationsByModelId = cq.where(cb.and(modelIdEq, yearBetweenStartAndEnd));
        TypedQuery<Modification> query = entityManager.createQuery(generationsByModelId);
        return query.getResultList();
    }

    public List<Modification> getSimilar(Modification modification, List<SimilarityTag> tagList) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> tupleQuery = cb.createTupleQuery();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Root<Modification> tupleRoot = tupleQuery.from(tClass);

        List<SimilarityPredicateAndGroupElement> predicateAndGroupElements = tagList.stream()
                .map(x -> similarityTagPredicateFromMap(x).getPredicate(modification, tupleRoot))
                .filter(Objects::nonNull).collect(Collectors.toList());
        List<Predicate> predicates = predicateAndGroupElements.stream()
                .map(SimilarityPredicateAndGroupElement::getPredicate)
                .filter(Objects::nonNull).collect(Collectors.toList());
        List<Expression<?>> groupElements = predicateAndGroupElements.stream()
                .map(SimilarityPredicateAndGroupElement::getGroupElement)
                .filter(Objects::nonNull).collect(Collectors.toList());
        groupElements.add(tupleRoot.get("chassis").get("id"));
        List<Selection<?>> multiselectElements = predicateAndGroupElements.stream()
                .map(SimilarityPredicateAndGroupElement::getGroupElement)
                .filter(Objects::nonNull).map(x -> (Selection<?>) x).collect(Collectors.toList());
        multiselectElements.add(tupleRoot.get("chassis").get("id"));
        multiselectElements.add(0, cb.min(tupleRoot.get("id")));

        tupleQuery.multiselect(multiselectElements);
        tupleQuery.groupBy(groupElements);
        tupleQuery.distinct(true);
        tupleQuery.where(cb.and(predicates.toArray(new Predicate[]{})));
        List<Integer> modIds
                = entityManager.createQuery(tupleQuery).getResultList().stream()
                .map(x -> x.get(0, Integer.class)).collect(Collectors.toList());

        cq.where(root.get("id").in(modIds));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    private SimilarityTagFilter similarityTagPredicateFromMap(SimilarityTag similarityTag) {
        if (!similarityTagPredicates.containsKey(similarityTag)) {
            similarityTagPredicates.put(similarityTag, similarityTagFilterFactory.getByTag(similarityTag));
        }
        return similarityTagPredicates.get(similarityTag);
    }
}
