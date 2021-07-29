package com.thirdwheel.carbase.service.carsmodelservices.specificservices;

import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.service.GenerationService;
import com.thirdwheel.carbase.service.carsmodelservices.AbstractCarsModelService;
import com.thirdwheel.carbase.service.enums.CarSearchDomain;
import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GenerationCarsModelService extends AbstractCarsModelService {
    private final GenerationService generationService;

    public GenerationCarsModelService(AbstractCarsModelService nextModelOfCarService, GenerationService generationService) {
        super(nextModelOfCarService);
        this.generationService = generationService;
    }

    @Override
    public List<CarSearchResponseElement> getByVendorAndNameSubstring(int vendorId, String nameSubstring) {
        List<CarSearchResponseElement> carSearchResponseElements
                = super.getByVendorAndNameSubstring(vendorId, nameSubstring);

        List<Generation> generations =
                generationService.getByVendorAndNameSubstringDistinctByName(vendorId, nameSubstring);

        generations.forEach(x -> {
            carSearchResponseElements.add(new CarSearchResponseElement(x, CarSearchDomain.GENERATION));
        });

        return carSearchResponseElements;
    }

    @Override
    public List<CarSearchResponseElement> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarSearchResponseElement> byVendorAndCarModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);

        List<Generation> modelByVendor = generationService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);

        modelByVendor.forEach(x -> {
            byVendorAndCarModelAndYear.add(new CarSearchResponseElement(x, CarSearchDomain.GENERATION));
        });

        return byVendorAndCarModelAndYear;
    }
}
