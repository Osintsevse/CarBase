package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.ModificationRepository;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModificationService extends GeneralService<Modification, ModificationRepository> {
    public ModificationService(ModificationRepository repository) {
        super(repository);
    }


    public List<Modification> getByVendor(Integer vendorId) {
        return repository.getByVendor(vendorId);
    }

    public List<Modification> getByChassisAndYear(int chassisId, String year) {
        return repository.getByChassisAndYear(chassisId, year);
    }

    public List<Modification> getByGenerationAndYear(int generationId, String year) {
        return repository.getByGenerationAndYear(generationId, year);
    }

    public List<Modification> getByModel(Integer modelId) {
        return repository.getByModel(modelId);
    }

    public List<Modification> getByModelAndYear(int modelId, String year) {
        return repository.getByModelAndYear(modelId, year);
    }

    public List<Modification> getSimilar(Integer modificationId, String[] tags) {
        return repository.getSimilar(this.getById(modificationId), SimilarityTag.getByTagsStringArray(tags));
    }
}
