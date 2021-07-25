package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Engine;
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
public class StrokeTagFilter extends AbstractTagFilter {
    public static final int DEVIATION_PERCENT = 10;

    public StrokeTagFilter() {
        super(SimilarityTag.STROKE);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Double valueForComparison = modification.getEngineModification().getEngine().getStroke();
        if (valueForComparison != null) {
            Double lower = valueForComparison * (100 - DEVIATION_PERCENT) / 100;
            Double higher = valueForComparison * (100 + DEVIATION_PERCENT) / 100;
            Path<Double> objectPath = root.get(Modification.Fields.engineModification)
                    .get(EngineModification.Fields.engine).get(Engine.Fields.stroke);
            return new SimilarityPredicateAndGroupElement(cb.between(objectPath, lower, higher), objectPath);
        } else {
            return null;
        }
    }
}
