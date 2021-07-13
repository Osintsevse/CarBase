package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.service.model.CarsModel;

import java.util.List;
import java.util.Map;

public interface IModelOfCarService {
    Map<String, CarsModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning);

    List<CarsModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year);
}
