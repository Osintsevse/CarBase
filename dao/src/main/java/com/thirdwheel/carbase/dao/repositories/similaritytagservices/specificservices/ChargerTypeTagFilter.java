package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.EngineModification;
import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.models.enums.ChargerType;
import com.thirdwheel.carbase.dao.models.enums.EngineType;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class ChargerTypeTagFilter extends AbstractTagFilter {
    public ChargerTypeTagFilter() {
        super(SimilarityTag.CHARGER_TYPE);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        ChargerType valueForComparison = modification.getEngineModification().getChargerType();
        if ((valueForComparison != null)) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            Path<EngineType> objectPath = root.get(Modification.Fields.engineModification)
                    .get(EngineModification.Fields.chargerType);
            return new SimilarityPredicateAndGroupElement(cb.equal(objectPath, valueForComparison), null);
        } else {
            return null;
        }
    }
}
