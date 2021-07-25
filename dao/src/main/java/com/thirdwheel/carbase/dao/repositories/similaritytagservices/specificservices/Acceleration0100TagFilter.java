package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;


public class Acceleration0100TagFilter extends AbstractTagFilter {
    public static final int DEVIATION_PERCENT = 5;

    public Acceleration0100TagFilter(EntityManager entityManager) {
        super(SimilarityTag.ACCELERATION0100, entityManager);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        if (modification.getAcceleration0100() != null) {
            double lower = modification.getAcceleration0100() * (100 - DEVIATION_PERCENT) / 100;
            double higher = modification.getAcceleration0100() * (100 + DEVIATION_PERCENT) / 100;
            return new SimilarityPredicateAndGroupElement(
                    cb.between(root.get("acceleration0100"), lower, higher),
                    root.get("acceleration0100"));
        } else {
            return null;
        }
    }
}
