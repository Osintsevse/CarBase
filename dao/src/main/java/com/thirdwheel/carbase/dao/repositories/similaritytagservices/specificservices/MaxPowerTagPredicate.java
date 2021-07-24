package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagPredicate;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class MaxPowerTagPredicate extends AbstractTagPredicate {
    public static final int DEVIATION_PERCENT = 10;

    public MaxPowerTagPredicate(EntityManager entityManager) {
        super(SimilarityTag.MAX_POWER, entityManager);
    }

    @Override
    public Predicate getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        int lower = (int) (modification.getEngineModification().getMaxPower() * (100 - DEVIATION_PERCENT) / 100);
        int higher = (int) (modification.getEngineModification().getMaxPower() * (100 + DEVIATION_PERCENT) / 100);
        return cb.and(
                cb.greaterThanOrEqualTo(root.get("engineModification").get("maxPower"), lower),
                cb.lessThanOrEqualTo(root.get("engineModification").get("maxPower"), higher));
    }
}
