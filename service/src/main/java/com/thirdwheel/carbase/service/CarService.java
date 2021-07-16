package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.carsmodelservices.CarsModelService;
import com.thirdwheel.carbase.service.enums.CarsModelType;
import com.thirdwheel.carbase.service.model.CarsModel;
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
            if (x.getCarsModelType() == CarsModelType.MODIFICATION) {
                modifications.add(modificationService.getById(x.getId()));
            } else if (x.getCarsModelType() == CarsModelType.CHASSIS) {
                modifications.addAll(modificationService.getByChassisAndYear(x.getId(), year));
            } else if (x.getCarsModelType() == CarsModelType.GENERATION) {
                modifications.addAll(modificationService.getByGenerationAndYear(x.getId(), year));
            } else if (x.getCarsModelType() == CarsModelType.MODEL) {
                modifications.addAll(modificationService.getByModelAndYear(x.getId(), year));
            }
        });
        return modifications;
    }
}
