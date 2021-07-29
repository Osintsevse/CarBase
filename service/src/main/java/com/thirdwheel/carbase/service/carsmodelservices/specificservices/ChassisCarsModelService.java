package com.thirdwheel.carbase.service.carsmodelservices.specificservices;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.service.ChassisService;
import com.thirdwheel.carbase.service.carsmodelservices.AbstractCarsModelService;
import com.thirdwheel.carbase.service.enums.CarSearchDomain;
import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ChassisCarsModelService extends AbstractCarsModelService {
    private final ChassisService chassisService;

    public ChassisCarsModelService(AbstractCarsModelService nextModelOfCarService, ChassisService chassisService) {
        super(nextModelOfCarService);
        this.chassisService = chassisService;
    }

    @Override
    public List<CarSearchResponseElement> getByVendorAndNameSubstring(int vendorId, String nameSubstring) {
        List<CarSearchResponseElement> carSearchResponseElements =
                super.getByVendorAndNameSubstring(vendorId, nameSubstring);

        List<Chassis> chassises = chassisService.getByVendorAndNameSubstringDistinctByName(vendorId, nameSubstring);

        chassises.forEach(x -> carSearchResponseElements.add(new CarSearchResponseElement(x, CarSearchDomain.CHASSIS)));

        return carSearchResponseElements;
    }

    @Override
    public List<CarSearchResponseElement> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarSearchResponseElement> byVendorAndCarModelAndYear =
                super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);

        List<Chassis> modelByVendor = chassisService.getByVendorAndCarsModel(vendorId, carsModelName);

        modelByVendor.forEach(x -> {
            byVendorAndCarModelAndYear.add(new CarSearchResponseElement(x, CarSearchDomain.CHASSIS));
        });

        return byVendorAndCarModelAndYear;
    }
}
