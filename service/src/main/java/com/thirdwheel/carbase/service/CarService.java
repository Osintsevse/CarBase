package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.enums.CarsModelsType;
import com.thirdwheel.carbase.service.model.CarsModel;
import com.thirdwheel.carbase.service.modelofcarservices.CarsModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarsModelService carsModelService;
    private final ModificationService modificationService;

    public TreeSet<Modification> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        TreeSet<Modification> modifications = new TreeSet<>(Modification::compareTo);
        List<CarsModel> byVendorAndCarsModelAndYear = carsModelService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        byVendorAndCarsModelAndYear.forEach(x -> {
            if (x.getCarsModelsType() == CarsModelsType.MODIFICATION) {
                modifications.add(modificationService.getById(x.getId()));
            } else if (x.getCarsModelsType() == CarsModelsType.CHASSIS) {
                modifications.addAll(modificationService.getByChassisAndYear(x.getId(), year));
            } else if (x.getCarsModelsType() == CarsModelsType.GENERATION) {
                modifications.addAll(modificationService.getByGenerationAndYear(x.getId(), year));
            } else if (x.getCarsModelsType() == CarsModelsType.MODEL) {
                modifications.addAll(modificationService.getByModelAndYear(x.getId(), year));
            }
        });
        return modifications;
    }
}
