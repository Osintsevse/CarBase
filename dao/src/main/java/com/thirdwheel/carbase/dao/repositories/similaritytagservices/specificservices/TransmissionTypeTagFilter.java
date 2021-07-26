package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.models.enums.TransmissionType;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class TransmissionTypeTagFilter extends AbstractTagFilter {
    public TransmissionTypeTagFilter() {
        super(SimilarityTag.TRANSMISSION_TYPE);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        TransmissionType valueForComparison = modification.getTransmissionType();
        if ((valueForComparison != null) && (valueForComparison != TransmissionType.UNKNOWN)) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            Path<TransmissionType> objectPath = root.get(Modification.Fields.transmissionType);
            return new SimilarityPredicateAndGroupElement(cb.equal(objectPath, valueForComparison), null);
        } else {
            return null;
        }
    }
}
