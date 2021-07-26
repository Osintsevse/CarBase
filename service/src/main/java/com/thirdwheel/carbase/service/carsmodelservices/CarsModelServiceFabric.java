package com.thirdwheel.carbase.service.carsmodelservices;

import com.thirdwheel.carbase.dao.models.enums.SearchFieldForVendor;
import com.thirdwheel.carbase.service.ChassisService;
import com.thirdwheel.carbase.service.GenerationService;
import com.thirdwheel.carbase.service.ModelService;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.service.carsmodelservices.specificservices.ChassisCarsModelService;
import com.thirdwheel.carbase.service.carsmodelservices.specificservices.GenerationCarsModelService;
import com.thirdwheel.carbase.service.carsmodelservices.specificservices.ModelCarsModelService;
import com.thirdwheel.carbase.service.carsmodelservices.specificservices.ModificationCarsModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarsModelServiceFabric {
    private final ChassisService chassisService;
    private final GenerationService generationService;
    private final ModelService modelService;
    private final ModificationService modificationService;

    public AbstractCarsModelService getCarsModelService(SearchFieldForVendor searchFieldForVendor, AbstractCarsModelService nextCarsModelService) {
        switch (searchFieldForVendor) {
            case SEARCH_IN_MODELS:
                return new ModelCarsModelService(nextCarsModelService, modelService);
            case SEARCH_IN_GENERATIONS:
                return new GenerationCarsModelService(nextCarsModelService, generationService);
            case SEARCH_IN_CHASSIS:
                return new ChassisCarsModelService(nextCarsModelService, chassisService);
            case SEARCH_IN_MODIFICATIONS:
                return new ModificationCarsModelService(nextCarsModelService, modificationService);
            default:
                throw new RuntimeException("The value of searchFieldForVendor is out of Enum");
        }
    }
}
