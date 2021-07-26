package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class SquarenessCoefficientTagFilter extends AbstractTagFilter {
    public static final double DEVIATION_PERCENT = 0.01;

    public SquarenessCoefficientTagFilter() {
        super(SimilarityTag.SQUARENESS_COEFFICIENT);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Long wheelBase = modification.getChassis().getWheelBase();
        Long frontTrack = modification.getChassis().getFrontTrack();
        Long rearTrack = modification.getChassis().getRearTrack();
        if ((wheelBase != null) && (frontTrack != null) && (rearTrack != null)) {
            double squarenessCoefficient = (double) wheelBase / (double) (Math.max(frontTrack, rearTrack));
            Double lower = squarenessCoefficient * (100 - DEVIATION_PERCENT) / 100;
            Double higher = squarenessCoefficient * (100 + DEVIATION_PERCENT) / 100;
            return new SimilarityPredicateAndGroupElement(
                    cb.between(root.get(Modification.Fields.chassis)
                            .get(Chassis.Fields.squarenessCoefficient), lower, higher),
                    null);
        } else {
            return null;
        }
    }
}
