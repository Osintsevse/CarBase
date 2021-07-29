package com.thirdwheel.carbase.dao.repositories.modificationrepositories;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.models.Model;
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
public class ModificationByModelRepository extends GeneralEntityWithIdRepository<Modification> {

    public ModificationByModelRepository() {
        super(Modification.class);
    }

    public List<Modification> getByModel(int modelId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate modelIdEq = getPredicateModificationByModel(modelId, cb, root);
        cq.where(modelIdEq);
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Modification> getByModelAndYear(int modelId, String year) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Modification> cq = cb.createQuery(tClass);
        Root<Modification> root = cq.from(tClass);

        Predicate modelIdEq = getPredicateModificationByModel(modelId, cb, root);
        Predicate yearBetweenStartAndEnd = predicateCreator
                .yearBetweenStartAndEnd(root.get(Modification.Fields.start), root.get(Modification.Fields.end), year);

        cq.where(cb.and(modelIdEq, yearBetweenStartAndEnd));
        cq.orderBy(cb.asc(root.get(Modification.Fields.name)));

        TypedQuery<Modification> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    private Predicate getPredicateModificationByModel(int modelId, CriteriaBuilder cb, Root<Modification> root) {
        return cb.equal(root.get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.id), modelId);
    }
}
