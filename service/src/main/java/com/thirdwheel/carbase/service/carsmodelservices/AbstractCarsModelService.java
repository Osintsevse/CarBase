package com.thirdwheel.carbase.service.carsmodelservices;

import com.thirdwheel.carbase.service.model.CarsModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractCarsModelService {
    private AbstractCarsModelService nextCarsModelService;

    public Map<String, CarsModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        if (nextCarsModelService != null) {
            return nextCarsModelService.getByVendorAndNameBeginning(vendorId, nameBeginning);
        } else {
            return new TreeMap<>();
        }
    }

    public List<CarsModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        if (nextCarsModelService != null) {
            return nextCarsModelService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        } else {
            return new ArrayList<>();
        }
    }
}