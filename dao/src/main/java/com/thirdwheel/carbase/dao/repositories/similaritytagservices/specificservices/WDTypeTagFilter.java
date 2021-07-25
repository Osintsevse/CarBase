package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.models.enums.WDType;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class WDTypeTagFilter extends AbstractTagFilter {
    public WDTypeTagFilter() {
        super(SimilarityTag.WD_TYPE);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        WDType valueForComparison = modification.getWdType();
        if ((valueForComparison != null) && (valueForComparison != WDType.Unknown)) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            Path<WDType> objectPath = root.get(Modification.Fields.wdType);
            return new SimilarityPredicateAndGroupElement(cb.equal(objectPath, valueForComparison), null);
        } else {
            return null;
        }
    }
}
