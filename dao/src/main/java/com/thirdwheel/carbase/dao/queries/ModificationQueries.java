package com.thirdwheel.carbase.dao.queries;

import com.thirdwheel.carbase.dao.models.*;

import javax.persistence.EntityManager;

public class ModificationQueries extends AbstractQueries<Modification> {
    public ModificationQueries(EntityManager entityManager) {
        super(entityManager, Modification.class, Modification.Fields.name);
    }

    public ModificationQueries setFullFetch() {
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.generation).fetch(Generation.Fields.model)
                .fetch(Model.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.rearSuspension);
        root.fetch(Modification.Fields.chassis).fetch(Chassis.Fields.frontSuspension);
        root.fetch(Modification.Fields.engineModification).fetch(EngineModification.Fields.engine)
                .fetch(Engine.Fields.vendor).fetch(Vendor.Fields.vendorsConfiguration);
        return this;
    }

    @Override
    public ModificationQueries byVendorId(Integer vendorId) {
        addPredicateByAnd(getCriteriaBuilder().equal(getLowestRoot().get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.vendor)
                .get(Vendor.Fields.id), vendorId));
        return this;
    }

    public ModificationQueries addSubqueryByMinId() {
        super.addSubqueryByMinId(Modification.Fields.id);
        return this;
    }

    public ModificationQueries byYearIsBetween(Integer year) {
        byYearIsBetween(getLowestRoot().get(Modification.Fields.start),
                getLowestRoot().get(Modification.Fields.end), year);
        return this;
    }

    public ModificationQueries byChassisNameIsEqual(String chassisName) {
        addPredicateByAnd(getCriteriaBuilder().equal(getLowestRoot().get(Modification.Fields.chassis)
                .get(Chassis.Fields.name), chassisName));
        return this;
    }

    public ModificationQueries byGenerationNameIsEqual(String generationName) {
        addPredicateByAnd(getCriteriaBuilder().equal(getLowestRoot().get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.name), generationName));
        return this;
    }

    public ModificationQueries byModelNameIsEqual(String modelName) {
        addPredicateByAnd(getCriteriaBuilder().equal(getLowestRoot().get(Modification.Fields.chassis)
                .get(Chassis.Fields.generation)
                .get(Generation.Fields.model)
                .get(Model.Fields.name), modelName));
        return this;
    }
}
