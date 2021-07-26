package com.thirdwheel.carbase.service.carsmodelservices.specificservices;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.service.ModelService;
import com.thirdwheel.carbase.service.carsmodelservices.AbstractCarsModelService;
import com.thirdwheel.carbase.service.enums.CarDomain;
import com.thirdwheel.carbase.service.model.CarModel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ModelCarsModelService extends AbstractCarsModelService {
    private final ModelService modelService;

    public ModelCarsModelService(AbstractCarsModelService nextModelOfCarService, ModelService modelService) {
        super(nextModelOfCarService);
        this.modelService = modelService;
    }

    @Override
    public Map<String, CarModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Map<String, CarModel> modelOfCarByVendorAndText = super.getByVendorAndNameBeginning(vendorId, nameBeginning);
        List<Model> modelByVendor = modelService.getByVendor(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new CarModel(x.getId(), x.getName(), CarDomain.MODEL));
        });
        return modelOfCarByVendorAndText;
    }

    @Override
    public List<CarModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarModel> byVendorAndCarModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        List<Model> modelByVendor = modelService.getByVendorAndCarsModelAndYear(vendorId, carsModelName);
        modelByVendor.forEach(x -> {
            byVendorAndCarModelAndYear.add(new CarModel(x.getId(), x.getName(), CarDomain.MODEL));
        });
        return byVendorAndCarModelAndYear;
    }
}
