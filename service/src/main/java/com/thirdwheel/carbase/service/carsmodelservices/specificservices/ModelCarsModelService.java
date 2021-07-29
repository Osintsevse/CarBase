package com.thirdwheel.carbase.service.carsmodelservices.specificservices;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.service.ModelService;
import com.thirdwheel.carbase.service.carsmodelservices.AbstractCarsModelService;
import com.thirdwheel.carbase.service.enums.CarSearchDomain;
import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ModelCarsModelService extends AbstractCarsModelService {
    private final ModelService modelService;

    public ModelCarsModelService(AbstractCarsModelService nextModelOfCarService, ModelService modelService) {
        super(nextModelOfCarService);
        this.modelService = modelService;
    }

    @Override
    public List<CarSearchResponseElement> getByVendorAndNameSubstring(int vendorId, String nameSubstring) {
        List<CarSearchResponseElement> carSearchResponseElements =
                super.getByVendorAndNameSubstring(vendorId, nameSubstring);

        List<Model> models = modelService.getByVendorAndNameSubstringDistinctByName(vendorId, nameSubstring);

        models.forEach(x -> {
            carSearchResponseElements.add(new CarSearchResponseElement(x, CarSearchDomain.MODEL));
        });

        return carSearchResponseElements;
    }

    @Override
    public List<CarSearchResponseElement> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarSearchResponseElement> byVendorAndCarModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);

        List<Model> modelByVendor = modelService.getByVendorAndCarsModelAndYear(vendorId, carsModelName);

        modelByVendor.forEach(x -> {
            byVendorAndCarModelAndYear.add(new CarSearchResponseElement(x, CarSearchDomain.MODEL));
        });

        return byVendorAndCarModelAndYear;
    }
}
