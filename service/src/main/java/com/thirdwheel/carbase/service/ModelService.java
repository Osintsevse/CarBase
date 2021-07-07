package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService extends GeneralService<Model> {
    public ModelService(ModelRepository repository) {
        super(repository);
    }

    public List<Model> getByVendor(Integer vendorId, String nameBeginning) {
        if (nameBeginning == null) {
            return ((ModelRepository) repository).getByVendor(vendorId);
        } else {
            return ((ModelRepository) repository).getByVendor(vendorId, nameBeginning);
        }

    }
}
