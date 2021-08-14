package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.queries.ModelQueries;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelRepository extends GeneralEntityWithIdRepository<Model>
        implements RepositoryWithGettingByVendor<Model> {
    public ModelRepository() {
        super(Model.class);
    }

    @Override
    public List<Model> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        return new ModelQueries(entityManager).addSubqueryByMinId().setGroupByNameInSubquery()
                .byVendorId(vendorId).byNameStartsWithOrHasSubstring(nameSubstring)
                .build().getResultList();
    }
}
