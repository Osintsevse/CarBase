package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.carsmodelservices.CarsModelService;
import com.thirdwheel.carbase.service.enums.CarDomain;
import com.thirdwheel.carbase.service.model.CarModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarsModelService carsModelService;
    private final ModificationService modificationService;

    public Set<Modification> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        Set<Modification> modifications = new TreeSet<>(Modification::compareTo);
        List<CarModel> byVendorAndCarModelAndYear = carsModelService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        byVendorAndCarModelAndYear.forEach(x -> {
            if (x.getCarDomain() == CarDomain.MODIFICATION) {
                modifications.add(modificationService.getById(x.getId()));
            } else if (x.getCarDomain() == CarDomain.CHASSIS) {
                modifications.addAll(modificationService.getByChassisAndYear(x.getId(), year));
            } else if (x.getCarDomain() == CarDomain.GENERATION) {
                modifications.addAll(modificationService.getByGenerationAndYear(x.getId(), year));
            } else if (x.getCarDomain() == CarDomain.MODEL) {
                modifications.addAll(modificationService.getByModelAndYear(x.getId(), year));
            }
        });
        return modifications;
    }
}
