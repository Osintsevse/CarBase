package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.repositories.ChassisRepository;
import com.thirdwheel.carbase.dao.repositories.GenerationRepository;
import com.thirdwheel.carbase.dao.repositories.ModelRepository;
import com.thirdwheel.carbase.dao.repositories.ModificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModificationFromSearchService {
    private final ModificationRepository modificationRepository;
    private final ChassisRepository chassisRepository;
    private final GenerationRepository generationRepository;
    private final ModelRepository modelRepository;

    public List<Modification> getByModificationNameAndYear(Integer modificationId, @Nullable Integer year) {
        Modification modificationById = modificationRepository.getById(modificationId);
        int vendorId = modificationById.getChassis().getGeneration().getModel().getVendor().getId();
        String modificationName = modificationById.getName();

        if (year != null) {
            return modificationRepository
                    .getByVendorAndNameAndYear(vendorId,
                            modificationName,
                            year);
        } else {
            return modificationRepository.getByVendorAndName(vendorId, modificationName);
        }
    }

    public List<Modification> getByChassisNameAndYear(Integer chassisId, @Nullable Integer year) {
        Chassis chassisById = chassisRepository.getById(chassisId);
        int vendorId = chassisById.getGeneration().getModel().getVendor().getId();
        String chassisName = chassisById.getName();

        if (year != null) {
            return modificationRepository.getByVendorIdAndChassisNameAndYear(vendorId, chassisName, year);
        } else {
            return modificationRepository.getByVendorIdAndChassisName(vendorId, chassisName);
        }
    }

    public List<Modification> getByGenerationNameAndYear(Integer chassisId, @Nullable Integer year) {
        Generation generationById = generationRepository.getById(chassisId);
        int vendorId = generationById.getModel().getVendor().getId();
        String generationName = generationById.getName();

        if (year != null) {
            return modificationRepository.getByVendorIdAndGenerationNameAndYear(vendorId, generationName, year);
        } else {
            return modificationRepository.getByVendorIdAndGenerationName(vendorId, generationName);
        }
    }

    public List<Modification> getByModelNameAndYear(Integer modelId, @Nullable Integer year) {
        Model modelById = modelRepository.getById(modelId);
        int vendorId = modelById.getVendor().getId();
        String modelName = modelById.getName();

        if (year != null) {
            return modificationRepository.getByVendorIdAndModelNameAndYear(vendorId, modelName, year);
        } else {
            return modificationRepository.getByVendorIdAndModelName(vendorId, modelName);
        }
    }
}
