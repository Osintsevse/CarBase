package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.service.ModelService;
import com.thirdwheel.carbase.service.enums.CarsModelsType;
import com.thirdwheel.carbase.service.model.CarsModel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ModelModelOfCarService extends AModelOfCarService {
    private final ModelService modelService;

    public ModelModelOfCarService(IModelOfCarService nextModelOfCarService, ModelService modelService) {
        super(nextModelOfCarService);
        this.modelService = modelService;
    }

    @Override
    public Map<String, CarsModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Map<String, CarsModel> modelOfCarByVendorAndText = super.getByVendorAndNameBeginning(vendorId, nameBeginning);
        List<Model> modelByVendor = modelService.getByVendor(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new CarsModel(x.getId(), x.getName(), CarsModelsType.MODEL));
        });
        return modelOfCarByVendorAndText;
    }

    @Override
    public List<CarsModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarsModel> byVendorAndCarsModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        List<Model> modelByVendor = modelService.getByVendorAndCarsModelAndYear(vendorId, carsModelName);
        modelByVendor.forEach(x -> {
            byVendorAndCarsModelAndYear.add(new CarsModel(x.getId(), x.getName(), CarsModelsType.MODEL));
        });
        return byVendorAndCarsModelAndYear;
    }
}
