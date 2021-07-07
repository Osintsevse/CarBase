package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.ModifficationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModificationService extends GeneralService<Modification> {
    public ModificationService(ModifficationRepository repository) {
        super(repository);
    }

    public List<Modification> getByVendor(Integer modelId, String year) {
        if (year == null) {
            return ((ModifficationRepository) repository).getByModel(modelId);
        } else {
            return ((ModifficationRepository) repository).getByModel(modelId, year);
        }

    }

    public List<Modification> getByVendorAndNameBeginning(Integer vendorId, String nameBeginning) {
        return ((ModifficationRepository) repository).getByVendorAndNameBeginning(vendorId, nameBeginning);
    }
}
