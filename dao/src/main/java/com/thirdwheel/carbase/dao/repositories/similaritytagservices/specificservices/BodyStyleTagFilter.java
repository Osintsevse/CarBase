package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.models.enums.BodyStyle;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class BodyStyleTagFilter extends AbstractTagFilter {
    public BodyStyleTagFilter() {
        super(SimilarityTag.BODY_STYLE);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        BodyStyle valueForComparison = modification.getChassis().getBodyStyle();
        if ((valueForComparison != null) && (valueForComparison != BodyStyle.UNKNOWN)) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            Path<BodyStyle> objectPath = root.get(Modification.Fields.chassis)
                    .get(Chassis.Fields.bodyStyle);
            return new SimilarityPredicateAndGroupElement(cb.equal(objectPath, valueForComparison), null);
        } else {
            return null;
        }
    }
}
