package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.dao.models.Model;
import com.thirdwheel.carbase.service.ModelService;
import com.thirdwheel.carbase.service.enums.TypeOfModelOfCar;
import com.thirdwheel.carbase.service.model.ModelOfCar;
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
    public Map<String, ModelOfCar> getByVendorAndText(int vendorId, String nameBeginning) {
        Map<String, ModelOfCar> modelOfCarByVendorAndText = super.getByVendorAndText(vendorId, nameBeginning);
        List<Model> modelByVendor = modelService.getByVendor(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new ModelOfCar(x.getId(), x.getName(), TypeOfModelOfCar.MODEL));
        });
        return modelOfCarByVendorAndText;
    }
}
