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
public class EngineTagFilter extends AbstractTagFilter {
    public EngineTagFilter() {
        super(SimilarityTag.ENGINE);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        int id = modification.getEngineModification().getEngine().getId();
        Path<Integer> objectPath = root.get(Modification.Fields.engineModification)
                .get(EngineModification.Fields.engine).get(Engine.Fields.id);
        return new SimilarityPredicateAndGroupElement(cb.equal(objectPath, id), null);
    }
}
