package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class FrontTrackTagFilter extends AbstractTagFilter {
    public static final int DEVIATION_PERCENT = 10;

    public FrontTrackTagFilter() {
        super(SimilarityTag.FRONT_TRACK);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Long valueForComparison = modification.getChassis().getFrontTrack();
        if (valueForComparison != null) {
            Long lower = valueForComparison * (100 - DEVIATION_PERCENT) / 100;
            Long higher = valueForComparison * (100 + DEVIATION_PERCENT) / 100;
            Path<Long> objectPath = root.get(Modification.Fields.chassis)
                    .get(Chassis.Fields.frontTrack);
            return new SimilarityPredicateAndGroupElement(cb.between(objectPath, lower, higher), objectPath);
        } else {
            return null;
        }
    }
}
