package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.models.VendorsConfiguration;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class VendorsConfigurationRepository extends GeneralEntityRepository<VendorsConfiguration> {
    public VendorsConfigurationRepository() {
        super(VendorsConfiguration.class);
    }

    public VendorsConfiguration getByVendor(Integer vendorId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VendorsConfiguration> cq = cb.createQuery(tClass);
        Root<VendorsConfiguration> root = cq.from(tClass);

        Predicate vendorIdEq = cb.equal(root.get(VendorsConfiguration.Fields.vendor)
                .get(Vendor.Fields.id), vendorId);

        cq.where(vendorIdEq);
        cq.orderBy(cb.asc(root.get(Vendor.Fields.name)));

        TypedQuery<VendorsConfiguration> query = entityManager.createQuery(cq);
        return query.getSingleResult();
    }

    public VendorsConfiguration getByVendor(Vendor vendor) {
        int vendorId = vendor.getId();
        if (vendorId != 0) {
            return this.getByVendor(vendorId);
        }
        return null;
    }
}