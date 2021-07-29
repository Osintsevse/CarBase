package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.repositories.GenerationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerationService extends GeneralService<Generation, GenerationRepository> {
    public GenerationService(GenerationRepository repository) {
        super(repository);
    }


    public List<Generation> getByVendor(Integer vendorId) {
        return repository.getByVendor(vendorId);
    }

    public List<Generation> getByVendorAndNameSubstring(Integer vendorId, String nameBeginning) {
        return repository.getByVendorAndNameSubstring(vendorId, nameBeginning);
    }

    public List<Generation> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameBeginning) {
        return repository.getByVendorAndNameSubstringDistinctByName(vendorId, nameBeginning);
    }

    public List<Generation> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        return repository.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
    }

}
