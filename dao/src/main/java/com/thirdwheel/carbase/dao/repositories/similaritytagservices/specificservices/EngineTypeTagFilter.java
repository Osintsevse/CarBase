package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Engine;
import com.thirdwheel.carbase.dao.models.EngineModification;
import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.models.enums.EngineType;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class EngineTypeTagFilter extends AbstractTagFilter {
    public EngineTypeTagFilter() {
        super(SimilarityTag.ENGINE_TYPE);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        EngineType valueForComparison = modification.getEngineModification().getEngine().getEngineType();
        if ((valueForComparison != null) && (valueForComparison != EngineType.Unknown)) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            Path<EngineType> objectPath = root.get(Modification.Fields.engineModification)
                    .get(EngineModification.Fields.engine).get(Engine.Fields.engineType);
            return new SimilarityPredicateAndGroupElement(cb.equal(objectPath, valueForComparison), null);
        } else {
            return null;
        }
    }
}
