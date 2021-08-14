package com.thirdwheel.carbase.dao.queries;

import com.thirdwheel.carbase.dao.models.Vendor;

import javax.persistence.EntityManager;

public class VendorQueries extends AbstractQueries<Vendor> {
    public VendorQueries(EntityManager entityManager) {
        super(entityManager, Vendor.class, Vendor.Fields.name);
    }

    @Override
    public VendorQueries byVendorId(Integer vendorId) {
        addPredicateByAnd(getCriteriaBuilder().equal(getLowestRoot()
                .get(Vendor.Fields.id), vendorId));
        return this;
    }

    public VendorQueries setConfigurationFetch() {
        root.fetch(Vendor.Fields.vendorsConfiguration);
        return this;
    }
}
