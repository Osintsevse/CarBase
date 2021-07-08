package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.service.GenerationService;
import com.thirdwheel.carbase.service.enums.TypeOfModelOfCar;
import com.thirdwheel.carbase.service.model.ModelOfCar;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class GenerationModelOfCarService extends AModelOfCarService {
    private final GenerationService generationService;

    public GenerationModelOfCarService(IModelOfCarService nextModelOfCarService, GenerationService generationService) {
        super(nextModelOfCarService);
        this.generationService = generationService;
    }

    @Override
    public Map<String, ModelOfCar> getByVendorAndText(int vendorId, String nameBeginning) {
        Map<String, ModelOfCar> modelOfCarByVendorAndText = super.getByVendorAndText(vendorId, nameBeginning);
        List<Generation> modelByVendor = generationService.getByVendor(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new ModelOfCar(x.getId(), x.getName(), TypeOfModelOfCar.GENERATION));
        });
        return modelOfCarByVendorAndText;
    }
}
