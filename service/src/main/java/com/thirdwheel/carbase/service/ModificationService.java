package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.ModificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModificationService extends GeneralService<Modification> {
    public ModificationService(ModificationRepository repository) {
        super(repository);
    }

    public List<Modification> getByVendor(Integer modelId, String year) {
        if (year == null) {
            return ((ModificationRepository) repository).getByModel(modelId);
        } else {
            return ((ModificationRepository) repository).getByModel(modelId, year);
        }

    }

    public List<Modification> getByVendorAndNameBeginning(Integer vendorId, String nameBeginning) {
        if (nameBeginning == null) {
            return ((ModificationRepository) repository).getByVendor(vendorId);
        } else {
            return ((ModificationRepository) repository).getByVendor(vendorId, nameBeginning);
        }
    }
}
