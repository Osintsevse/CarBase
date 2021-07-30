package com.thirdwheel.carbase.dao.repositories.similaritytagservices.specificservices;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.models.Suspension;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.AbstractTagFilter;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityPredicateAndGroupElement;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Service
public class RearSuspensionTagFilter extends AbstractTagFilter {
    public RearSuspensionTagFilter() {
        super(SimilarityTag.REAR_SUSPENSION);
    }

    @Override
    public SimilarityPredicateAndGroupElement getPredicate(Modification modification, Root<Modification> root) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        int id = modification.getChassis().getRearSuspension().getId();
        Path<Integer> objectPath = root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.rearSuspension).get(Suspension.Fields.id);
        return new SimilarityPredicateAndGroupElement(cb.equal(objectPath, id), null);
    }
}
