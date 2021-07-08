package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.service.model.ModelOfCar;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.TreeMap;

@NoArgsConstructor
@AllArgsConstructor
public abstract class AModelOfCarService implements IModelOfCarService {
    private IModelOfCarService nextModelOfCarService;

    @Override
    public Map<String, ModelOfCar> getByVendorAndText(int vendorId, String nameBeginning) {
        if (nextModelOfCarService != null) {
            return nextModelOfCarService.getByVendorAndText(vendorId, nameBeginning);
        } else {
            return new TreeMap<>();
        }
    }
}
