package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagPredicate;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class WDTypeTagPredicate extends AbstractTagPredicate {
    public WDTypeTagPredicate(EntityManager entityManager) {
        super(SimilarityTag.WD_TYPE, entityManager);
    }

    @Override
    public Predicate getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        return cb.equal(root.get("wdType"), modification.getWdType());
    }
}
