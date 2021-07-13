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

    public List<Modification> getByVendorAndYear(Integer modelId, String year) {
        if (year == null) {
            return ((ModificationRepository) repository).getByModel(modelId);
        } else {
            return ((ModificationRepository) repository).getByModelAndYear(modelId, year);
        }
    }


    public List<Modification> getByVendorAndNameBeginning(Integer vendorId, String nameBeginning) {
        if (nameBeginning == null) {
            return ((ModificationRepository) repository).getByVendor(vendorId);
        } else {
            return ((ModificationRepository) repository).getByVendorAndNameBeginning(vendorId, nameBeginning);
        }
    }

    public List<Modification> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        return ((ModificationRepository) repository).getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
    }

    public List<Modification> getByChassisAndYear(int chassisId, String year) {
        return ((ModificationRepository) repository).getByChassisAndYear(chassisId, year);
    }

    public List<Modification> getByGenerationAndYear(int generationId, String year) {
        return ((ModificationRepository) repository).getByGenerationAndYear(generationId, year);
    }

    public List<Modification> getByModelAndYear(int modelId, String year) {
        return ((ModificationRepository) repository).getByModelAndYear(modelId, year);
    }
}
