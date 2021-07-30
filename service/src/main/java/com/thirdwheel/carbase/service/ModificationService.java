package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.excetions.CarbaseEntityNotFoundException;
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

    public List<Modification> getSimilar(Integer modificationId, String[] tags) {
        Modification modificationById = this.getById(modificationId);
        if (modificationById == null) {
            throw new CarbaseEntityNotFoundException("Modification not found for id: " + modificationId);
        }

        return repository.getSimilar(modificationById, SimilarityTag.getByTagsStringArray(tags));
    }
}
