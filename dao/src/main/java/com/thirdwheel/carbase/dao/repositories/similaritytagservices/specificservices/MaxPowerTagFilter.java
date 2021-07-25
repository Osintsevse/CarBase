package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;


public class MaxPowerTagFilter extends AbstractTagFilter {
    public static final int DEVIATION_PERCENT = 10;

    public MaxPowerTagFilter(EntityManager entityManager) {
        super(SimilarityTag.MAX_POWER, entityManager);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        if (modification.getEngineModification().getMaxPower() != null) {
            int lower = modification.getEngineModification().getMaxPower() * (100 - DEVIATION_PERCENT) / 100;
            int higher = modification.getEngineModification().getMaxPower() * (100 + DEVIATION_PERCENT) / 100;
            return new SimilarityPredicateAndGroupElement(
                    cb.between(root.get("engineModification").get("maxPower"), lower, higher),
                    root.get("engineModification").get("maxPower"));
        } else {
            return null;
        }
    }
}
