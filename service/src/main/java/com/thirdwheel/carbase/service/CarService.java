package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.models.enums.CarSearchDomain;
import com.thirdwheel.carbase.service.carsearchservices.CarSearchRequestService;
import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarSearchRequestService carSearchRequestService;
    private final ModificationService modificationService;

    public Set<Modification> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        Set<Modification> modifications = new TreeSet<>(Modification::compareTo);
        List<CarSearchResponseElement> byVendorAndCarModelAndYear =
                carSearchRequestService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);

        byVendorAndCarModelAndYear.forEach(x -> {
            if (x.getCarSearchDomain() == CarSearchDomain.MODIFICATION) {
                modifications.add(modificationService.getById(x.getEntityWithName().getId()));
            } else if (x.getCarSearchDomain() == CarSearchDomain.CHASSIS) {
                modifications.addAll(modificationService.getByChassisAndYear(x.getEntityWithName().getId(), year));
            } else if (x.getCarSearchDomain() == CarSearchDomain.GENERATION) {
                modifications.addAll(modificationService.getByGenerationAndYear(x.getEntityWithName().getId(), year));
            } else if (x.getCarSearchDomain() == CarSearchDomain.MODEL) {
                modifications.addAll(modificationService.getByModelAndYear(x.getEntityWithName().getId(), year));
            }
        });

        return modifications;
    }
}
