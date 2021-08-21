package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.queries.GenerationQueries;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerationRepository extends GeneralEntityWithIdRepository<Generation>
        implements RepositoryWithGettingByVendor<Generation> {
    public GenerationRepository() {
        super(Generation.class);
    }

    @Override
    public List<Generation> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        GenerationQueries generationQueries = new GenerationQueries(entityManager);
        generationQueries.addSubqueryByMinId().setGroupByNameInSubquery()
                .byVendorId(vendorId).byNameStartsWithOrHasSubstring(nameSubstring);
        return generationQueries.build().getResultList();
    }
}
