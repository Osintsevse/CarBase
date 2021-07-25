package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.models.enums.WDType;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;


public class WDTypeTagFilter extends AbstractTagFilter {
    public WDTypeTagFilter(EntityManager entityManager) {
        super(SimilarityTag.WD_TYPE, entityManager);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        if ((modification.getWdType() != null) && (modification.getWdType() != WDType.Unknown)) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            return new SimilarityPredicateAndGroupElement(
                    cb.equal(root.get("wdType"), modification.getWdType()),
                    null);
        } else {
            return null;
        }
    }
}
