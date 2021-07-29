package com.thirdwheel.carbase.dao.repositories.modificationrepositories;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.GeneralEntityWithIdRepository;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ModificationByGenerationRepository extends GeneralEntityWithIdRepository<Modification> {

    public ModificationByGenerationRepository() {
        super(Modification.class);
    }

    public List<Modification> getByGenerationAndYear(int generationId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);

        Root<Modification> root = cq.from(tClass);

        Predicate idEquals = cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.id), generationId);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);

        cq.where(cb.and(idEquals, yearBetweenStartAndEnd));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));


        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

}
