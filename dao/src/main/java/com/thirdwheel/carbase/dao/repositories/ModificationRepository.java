package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTagPredicate;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTagPredicateFactory;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ModificationRepository extends GeneralEntityWithNameRepository<Modification> {
    private final SimilarityTagPredicateFactory similarityTagPredicateFactory;
    private final Map<SimilarityTag, SimilarityTagPredicate> similarityTagPredicates = new HashMap<>();

    public ModificationRepository(SimilarityTagPredicateFactory similarityTagPredicateFactory) {
        super(Modification.class);
        this.similarityTagPredicateFactory = similarityTagPredicateFactory;
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
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        List<Predicate> predicates = tagList.stream()
                .map(x -> similarityTagPredicateFromMap(x).getPredicate(modification, root))
                .collect(Collectors.toList());
        CriteriaQuery<Modification> byPredicates = cq.where(cb.and(predicates.toArray(new Predicate[]{})));

        TypedQuery<Modification> query = entityManager.createQuery(byPredicates);
        return query.getResultList();
    }

    private SimilarityTagPredicate similarityTagPredicateFromMap(SimilarityTag similarityTag) {
        if (!similarityTagPredicates.containsKey(similarityTag)) {
            similarityTagPredicates.put(similarityTag, similarityTagPredicateFactory.getByTag(similarityTag));
        }
        return similarityTagPredicates.get(similarityTag);
    }
}
