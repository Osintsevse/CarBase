package com.thirdwheel.carbase.dao.queries;

import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.models.Vendor;

import javax.persistence.EntityManager;

public class GenerationQueries extends AbstractQueries<Generation> {
    public GenerationQueries(EntityManager entityManager) {
        super(entityManager, Generation.class, Generation.Fields.name);
    }

    @Override
    public GenerationQueries byVendorId(Integer vendorId) {
        addPredicateByAnd(getCriteriaBuilder().equal(getLowestRoot()
                .get(Generation.Fields.model)
                .get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId));
        return this;
    }

    public GenerationQueries addSubqueryByMinId() {
        super.addSubqueryByMinId(Generation.Fields.id);
        return this;
    }
}
