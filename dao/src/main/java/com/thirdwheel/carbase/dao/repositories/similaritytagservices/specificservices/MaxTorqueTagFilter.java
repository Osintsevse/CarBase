package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.EngineModification;
import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class MaxTorqueTagFilter extends AbstractTagFilter {
    public static final int DEVIATION_PERCENT = 10;

    public MaxTorqueTagFilter() {
        super(SimilarityTag.MAX_TORQUE);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Integer valueForComparison = modification.getEngineModification().getMaxTorque();
        if (valueForComparison != null) {
            Integer lower = valueForComparison * (100 - DEVIATION_PERCENT) / 100;
            Integer higher = valueForComparison * (100 + DEVIATION_PERCENT) / 100;
            Path<Integer> objectPath = root.get(Modification.Fields.engineModification)
                    .get(EngineModification.Fields.maxTorque);
            return new SimilarityPredicateAndGroupElement(cb.between(objectPath, lower, higher), objectPath);
        } else {
            return null;
        }
    }
}
