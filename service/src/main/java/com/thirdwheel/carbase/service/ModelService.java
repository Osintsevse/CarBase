package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService extends GeneralService<Model, ModelRepository> {
    public ModelService(ModelRepository repository) {
        super(repository);
    }

    public List<Model> getByVendor(Integer vendorId) {
        return repository.getByVendor(vendorId);
    }

    public List<Model> getByVendorAndNameSubstring(Integer vendorId, String nameSubstring) {
        return repository.getByVendorAndNameSubstring(vendorId, nameSubstring);
    }

    public List<Model> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        return repository.getByVendorAndNameSubstringDistinctByName(vendorId, nameSubstring);
    }

    public List<Model> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName) {
        return repository.getByVendorAndCarsModelAndYear(vendorId, carsModelName);
    }

}
