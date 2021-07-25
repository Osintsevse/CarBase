package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.IEntityWithName;
import com.thirdwheel.carbase.dao.models.Vendor;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class VendorRepository extends GeneralEntityRepository<Vendor> {
    public VendorRepository() {
        super(Vendor.class);
    }

    public List<Vendor> getByNameBeginning(String nameBeginning) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vendor> cq = cb.createQuery(Vendor.class);
        Root<Vendor> rootEntry = cq.from(Vendor.class);
        Predicate nameIsLike = predicateCreator.stringStartsWith(rootEntry.get(Vendor.Fields.name), nameBeginning);
        CriteriaQuery<Vendor> cqByNameBeginning = cq.where(nameIsLike);
        TypedQuery<Vendor> query = entityManager.createQuery(cqByNameBeginning);
        return query.getResultList();
    }
}
