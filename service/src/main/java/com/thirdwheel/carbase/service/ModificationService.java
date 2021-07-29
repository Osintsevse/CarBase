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

    public List<Modification> getSimilar(Integer modificationId, String[] tags) {
        return repository.getSimilar(this.getById(modificationId), SimilarityTag.getByTagsStringArray(tags));
    }
}
