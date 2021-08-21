package com.thirdwheel.carbase.dao.queries;

import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.models.Vendor;

import javax.persistence.EntityManager;

public class ModelQueries extends AbstractQueries<Model> {
    public ModelQueries(EntityManager entityManager) {
        super(entityManager, Model.class, Model.Fields.name);
    }

    @Override
    public ModelQueries byVendorId(Integer vendorId) {
        addPredicateByAnd(getCriteriaBuilder().equal(getLowestRoot()
                .get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId));
        return this;
    }

    public ModelQueries addSubqueryByMinId() {
        super.addSubqueryByMinId(Model.Fields.id);
        return this;
    }
    
    public ModelQueries fetchAll() {
        root.fetch(Model.Fields.vendor)
                .fetch(Vendor.Fields.vendorsConfiguration);
        return this;
    }
}
