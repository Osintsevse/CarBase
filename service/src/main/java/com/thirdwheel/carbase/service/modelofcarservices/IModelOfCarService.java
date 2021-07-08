package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.service.model.ModelOfCar;

import java.util.Map;

public interface IModelOfCarService {
    Map<String, ModelOfCar> getByVendorAndText(int vendorId, String nameBeginning);
}
