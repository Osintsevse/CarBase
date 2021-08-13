package com.thirdwheel.carbase.dao.querybuilders;

import com.thirdwheel.carbase.dao.models.*;

import javax.persistence.EntityManager;

public class ModificationQueries extends AbstractQueries<Modification> {
    public ModificationQueries(EntityManager entityManager) {
        super(entityManager, Modification.class, Modification.Fields.name);
    }

    @Override
    public AbstractQueries<Modification> setFetch() {
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.generation).fetch(Generation.Fields.model)
                .fetch(Model.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.rearSuspension);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.frontSuspension);
        root.fetch(Modification.Fields.engineModification).fetch(EngineModification.Fields.engine)
                .fetch(Engine.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);
        return this;
    }

    @Override
    public AbstractQueries<Modification> byVendorId(Integer vendorId) {
        addPredicateAnd(getCriteriaBuilder().equal(getLowestRoot().get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId));
        return this;
    }

    @Override
    public ModificationQueries setSubqueryByMinId() {
        super.setSubqueryByMinId(Modification.Fields.id);
        return this;
    }

    @Override
    public ModificationQueries setYearIsBetween(Integer year) {
        setYearIsBetween(getLowestRoot().get(Modification.Fields.start),
                getLowestRoot().get(Modification.Fields.end), year);
        return this;
    }

    public ModificationQueries setChassisNameIsEqual(String chassisName) {
        addPredicateAnd(getCriteriaBuilder().equal(getLowestRoot().get(Modification.Fields.chassis)
                .get(Chassis.Fields.name), chassisName));
        return this;
    }

    public ModificationQueries setGenerationNameIsEqual(String generationName) {
        addPredicateAnd(getCriteriaBuilder().equal(getLowestRoot().get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.name), generationName));
        return this;
    }

    public ModificationQueries setModelNameIsEqual(String modelName) {
        addPredicateAnd(getCriteriaBuilder().equal(getLowestRoot().get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.name), modelName));
        return this;
    }
}
