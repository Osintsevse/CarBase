package com.thirdwheel.carbase.dao.queries;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.models.Vendor;

import javax.persistence.EntityManager;

public class ChassisQueries extends AbstractQueries<Chassis> {
    public ChassisQueries(EntityManager entityManager) {
        super(entityManager, Chassis.class, Chassis.Fields.name);
    }

    @Override
    public ChassisQueries byVendorId(Integer vendorId) {
        addPredicateByAnd(getCriteriaBuilder().equal(getLowestRoot()
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId));
        return this;
    }

    public ChassisQueries addSubqueryByMinId() {
        super.addSubqueryByMinId(Chassis.Fields.id);
        return this;
    }

    public ChassisQueries fetchAll() {
        root.fetch(Chassis.Fields.generation)
                .fetch(Generation.Fields.model)
                .fetch(Model.Fields.vendor)
                .fetch(Vendor.Fields.vendorsConfiguration);
        root.fetch(Chassis.Fields.frontSuspension);
        root.fetch(Chassis.Fields.rearSuspension);
        return this;
    }
}
