package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.modificationrepositories.*;
import com.thirdwheel.carbase.dao.repositories.similaritytagservices.SimilarityTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModificationRepository implements RepositoryWithGettingByVendor<Modification>,
        EntityWithIdRepository<Modification> {
    private final ModificationByVendorRepository modificationByVendorRepository;
    private final ModificationByModelRepository modificationByModelRepository;
    private final ModificationByGenerationRepository modificationByGenerationRepository;
    private final ModificationByChassisRepository modificationByChassisRepository;
    private final ModificationSimilarityRepository modificationSimilarityRepository;

    @Override
    public void save(Modification entity) {
        modificationByVendorRepository.save(entity);
    }

    @Override
    public Modification getById(int id) {
        return modificationByVendorRepository.getById(id);
    }

    @Override
    public List<Modification> getAll() {
        return modificationByVendorRepository.getAll();
    }

    public List<Modification> getByModel(int modelId) {
        return modificationByModelRepository.getByModel(modelId);
    }

    public List<Modification> getByModelAndYear(int modelId, String year) {
        return modificationByModelRepository.getByModelAndYear(modelId, year);
    }

    public List<Modification> getByVendor(int vendorId) {
        return modificationByVendorRepository.getByVendor(vendorId);
    }

    @Override
    public List<Modification> getByVendorAndNameSubstring(Integer vendorId, String nameSubstring) {
        return modificationByVendorRepository.getByVendorAndNameSubstring(vendorId, nameSubstring);
    }

    @Override
    public List<Modification> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        return modificationByVendorRepository.getByVendorAndNameSubstringDistinctByName(vendorId, nameSubstring);
    }

    @Override
    public List<Modification> getByVendorAndNameAndYear(Integer vendorId, String name, String year) {
        return modificationByVendorRepository.getByVendorAndNameAndYear(vendorId, name, year);
    }

    public List<Modification> getByVendorAndName(Integer vendorId, String name) {
        return modificationByVendorRepository.getByVendorAndName(vendorId, name);
    }

    public List<Modification> getByChassisAndYear(int chassisId, String year) {
        return modificationByChassisRepository.getByChassisAndYear(chassisId, year);
    }

    public List<Modification> getByGenerationAndYear(int generationId, String year) {
        return modificationByGenerationRepository.getByGenerationAndYear(generationId, year);
    }

    public List<Modification> getSimilar(Modification modification, List<SimilarityTag> tagList) {
        return modificationSimilarityRepository.getSimilar(modification, tagList);
    }

    public List<Modification> getByVendorIdAndChassisNameAndYear(Integer vendorId, String chassisName, String year) {
        return modificationByVendorRepository.getByVendorIdAndChassisNameAndYear(vendorId, chassisName, year);
    }

    public List<Modification> getByVendorIdAndChassisName(Integer vendorId, String chassisName) {
        return modificationByVendorRepository.getByVendorIdAndChassisName(vendorId, chassisName);
    }

    public List<Modification> getByVendorIdAndModelNameAndYear(Integer vendorId, String modelName, String year) {
        return modificationByVendorRepository.getByVendorIdAndModelNameAndYear(vendorId, modelName, year);
    }

    public List<Modification> getByVendorIdAndModelName(Integer vendorId, String modelName) {
        return modificationByVendorRepository.getByVendorIdAndModelName(vendorId, modelName);
    }

    public List<Modification> getByVendorIdAndGenerationNameAndYear(Integer vendorId, String generationName, String year) {
        return modificationByVendorRepository.getByVendorIdAndGenerationNameAndYear(vendorId, generationName, year);
    }

    public List<Modification> getByVendorIdAndGenerationName(Integer vendorId, String generationName) {
        return modificationByVendorRepository.getByVendorIdAndGenerationName(vendorId, generationName);
    }
}
