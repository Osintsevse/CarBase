package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.service.enums.CarsModelsType;
import com.thirdwheel.carbase.service.model.ModelOfCar;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ModificationModelOfCarService extends AModelOfCarService {
    private final ModificationService modificationService;

    public ModificationModelOfCarService(IModelOfCarService nextModelOfCarService, ModificationService modificationService) {
        super(nextModelOfCarService);
        this.modificationService = modificationService;
    }

    @Override
    public Map<String, ModelOfCar> getByVendorAndText(int vendorId, String nameBeginning) {
        Map<String, ModelOfCar> modelOfCarByVendorAndText = super.getByVendorAndText(vendorId, nameBeginning);
        List<Modification> modelByVendor = modificationService.getByVendorAndNameBeginning(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new ModelOfCar(x.getId(), x.getName(), CarsModelsType.MODIFICATIONS));
        });
        return modelOfCarByVendorAndText;
    }
}
