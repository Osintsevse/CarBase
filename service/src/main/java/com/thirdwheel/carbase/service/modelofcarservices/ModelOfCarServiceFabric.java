package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.dao.models.enums.SearchFieldForVendor;
import com.thirdwheel.carbase.service.ChassisService;
import com.thirdwheel.carbase.service.GenerationService;
import com.thirdwheel.carbase.service.ModelService;
import com.thirdwheel.carbase.service.ModificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelOfCarServiceFabric {
    private final ChassisService chassisService;
    private final GenerationService generationService;
    private final ModelService modelService;
    private final ModificationService modificationService;

    public ModelModelOfCarService getModelModelOfCarService(IModelOfCarService nextModelOfCarService) {
        return new ModelModelOfCarService(nextModelOfCarService, modelService);
    }

    public GenerationModelOfCarService getGenerationModelOfCarService(IModelOfCarService nextModelOfCarService) {
        return new GenerationModelOfCarService(nextModelOfCarService, generationService);
    }

    public ChassisModelOfCarService getChassisModelOfCarService(IModelOfCarService nextModelOfCarService) {
        return new ChassisModelOfCarService(nextModelOfCarService, chassisService);
    }

    public ModificationModelOfCarService getModifficationModelOfCarService(IModelOfCarService nextModelOfCarService) {
        return new ModificationModelOfCarService(nextModelOfCarService, modificationService);
    }

    public IModelOfCarService getModelOfCarService(SearchFieldForVendor searchFieldForVendor, IModelOfCarService nextModelOfCarService) {
        if (searchFieldForVendor == SearchFieldForVendor.SEARCH_IN_MODELS) {
            return getModelModelOfCarService(nextModelOfCarService);
        } else if (searchFieldForVendor == SearchFieldForVendor.SEARCH_IN_GENERATIONS) {
            return getGenerationModelOfCarService(nextModelOfCarService);
        } else if (searchFieldForVendor == SearchFieldForVendor.SEARCH_IN_CHASSIS) {
            return getChassisModelOfCarService(nextModelOfCarService);
        } else if (searchFieldForVendor == SearchFieldForVendor.SEARCH_IN_MODIFICATIONS) {
            return getModifficationModelOfCarService(nextModelOfCarService);
        }
        return null;
    }
}
