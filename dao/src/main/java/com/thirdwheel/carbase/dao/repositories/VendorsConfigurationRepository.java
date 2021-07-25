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
        Root<VendorsConfiguration> modelRoot = cq.from(tClass);
        Predicate vendorIdEq = predicateCreator
                .intIsEqual(modelRoot.get(VendorsConfiguration.Fields.vendor)
                        .get(Vendor.Fields.id), vendorId);
        CriteriaQuery<VendorsConfiguration> modelsByVendorId = cq.where(vendorIdEq);
        TypedQuery<VendorsConfiguration> query = entityManager.createQuery(modelsByVendorId);
        List<VendorsConfiguration> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        } else return null;
    }

    public VendorsConfiguration getByVendor(Vendor vendor) {
        int vendorId = vendor.getId();
        if (vendorId != 0) {
            return this.getByVendor(vendorId);
        }
        return null;
    }
}