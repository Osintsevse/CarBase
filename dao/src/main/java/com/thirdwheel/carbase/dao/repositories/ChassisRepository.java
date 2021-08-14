package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.queries.ChassisQueries;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChassisRepository extends GeneralEntityWithIdRepository<Chassis>
        implements RepositoryWithGettingByVendor<Chassis> {
    public ChassisRepository() {
        super(Chassis.class);
    }

    @Override
    public List<Chassis> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        return new ChassisQueries(entityManager).addSubqueryByMinId().setGroupByNameInSubquery()
                .byVendorId(vendorId).byNameStartsWithOrHasSubstring(nameSubstring)
                .build().getResultList();
    }
}
