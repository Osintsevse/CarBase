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
public class CarsModelServiceFabric {
    private final ChassisService chassisService;
    private final GenerationService generationService;
    private final ModelService modelService;
    private final ModificationService modificationService;

    public ModelCarsModelService getModelCarsModelService(ICarsModelService nextCarsModelService) {
        return new ModelCarsModelService(nextCarsModelService, modelService);
    }

    public GenerationCarsModelService getGenerationCarsModelService(ICarsModelService nextCarsModelService) {
        return new GenerationCarsModelService(nextCarsModelService, generationService);
    }

    public ChassisCarsModelService getChassisCarsModelService(ICarsModelService nextCarsModelService) {
        return new ChassisCarsModelService(nextCarsModelService, chassisService);
    }

    public ModificationCarsModelService getModificationCarsModelService(ICarsModelService nextCarsModelService) {
        return new ModificationCarsModelService(nextCarsModelService, modificationService);
    }

    public ICarsModelService getCarsModelService(SearchFieldForVendor searchFieldForVendor, ICarsModelService nextCarsModelService) {
        if (searchFieldForVendor == SearchFieldForVendor.SEARCH_IN_MODELS) {
            return getModelCarsModelService(nextCarsModelService);
        } else if (searchFieldForVendor == SearchFieldForVendor.SEARCH_IN_GENERATIONS) {
            return getGenerationCarsModelService(nextCarsModelService);
        } else if (searchFieldForVendor == SearchFieldForVendor.SEARCH_IN_CHASSIS) {
            return getChassisCarsModelService(nextCarsModelService);
        } else if (searchFieldForVendor == SearchFieldForVendor.SEARCH_IN_MODIFICATIONS) {
            return getModificationCarsModelService(nextCarsModelService);
        }
        return null;
    }
}
