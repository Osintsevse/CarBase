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


    public List<Generation> getByVendor(Integer vendorId, String nameBeginning) {
        if (nameBeginning == null) {
            return repository.getByVendor(vendorId);
        } else {
            return repository.getByVendorAndNameBeginning(vendorId, nameBeginning);
        }
    }

    public List<Generation> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        return repository.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
    }

}
