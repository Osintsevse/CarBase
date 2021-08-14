package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.queries.VendorQueries;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorRepository extends GeneralEntityWithIdRepository<Vendor> {
    public VendorRepository() {
        super(Vendor.class);
    }

    public List<Vendor> getByNameBeginning(String nameSubstring) {
        VendorQueries vendorQueries = new VendorQueries(entityManager).setConfigurationFetch();
        vendorQueries.byNameStartsWithOrHasSubstring(nameSubstring);
        return vendorQueries.build().getResultList();
    }

    @Override
    public List<Vendor> getAll() {
        VendorQueries vendorQueries = new VendorQueries(entityManager).setConfigurationFetch();
        return vendorQueries.build().getResultList();
    }

    @Override
    public Vendor getById(int id) {
        VendorQueries vendorQueries = new VendorQueries(entityManager).setConfigurationFetch();
        vendorQueries.byVendorId(id);
        return vendorQueries.build().getSingleResult();
    }
}
