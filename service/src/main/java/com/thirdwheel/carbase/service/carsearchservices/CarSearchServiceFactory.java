package com.thirdwheel.carbase.service.carsearchservices;

import com.thirdwheel.carbase.dao.models.enums.CarSearchDomain;
import com.thirdwheel.carbase.dao.repositories.ChassisRepository;
import com.thirdwheel.carbase.dao.repositories.GenerationRepository;
import com.thirdwheel.carbase.dao.repositories.ModelRepository;
import com.thirdwheel.carbase.dao.repositories.ModificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarSearchServiceFactory {
    private final ChassisRepository chassisRepository;
    private final GenerationRepository generationRepository;
    private final ModelRepository modelRepository;
    private final ModificationRepository modificationRepository;

    public AbstractCarSearchService getCarsModelService(CarSearchDomain carSearchDomain,
                                                        AbstractCarSearchService nextCarsModelService) {
        switch (carSearchDomain) {
            case MODEL:
                return new CertainCarSearchService(nextCarsModelService, modelRepository, carSearchDomain);
            case GENERATION:
                return new CertainCarSearchService(nextCarsModelService, generationRepository, carSearchDomain);
            case CHASSIS:
                return new CertainCarSearchService(nextCarsModelService, chassisRepository, carSearchDomain);
            case MODIFICATION:
                return new CertainCarSearchService(nextCarsModelService, modificationRepository, carSearchDomain);
            default:
                throw new RuntimeException("The value of searchFieldForVendor is out of Enum");
        }
    }
}
