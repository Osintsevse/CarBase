package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.service.model.CarsModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@NoArgsConstructor
@AllArgsConstructor
public abstract class ACarsModelService implements ICarsModelService {
    private ICarsModelService nextCarsModelService;

    @Override
    public Map<String, CarsModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        if (nextCarsModelService != null) {
            return nextCarsModelService.getByVendorAndNameBeginning(vendorId, nameBeginning);
        } else {
            return new TreeMap<>();
        }
    }

    @Override
    public List<CarsModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        if (nextCarsModelService != null) {
            return nextCarsModelService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        } else {
            return new ArrayList<>();
        }
    }
}
