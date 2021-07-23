package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagPredicate;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class Acceleration0100TagPredicate extends AbstractTagPredicate {
    public Acceleration0100TagPredicate(EntityManager entityManager) {
        super(SimilarityTag.ACCELERATION0100, entityManager);
    }

    @Override
    public Predicate getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        return cb.equal(root.get("acceleration0100"), modification.getAcceleration0100());
    }
}
