package com.thirdwheel.carbase.service.carsmodelservices.specificservices;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.service.ChassisService;
import com.thirdwheel.carbase.service.carsmodelservices.AbstractCarsModelService;
import com.thirdwheel.carbase.service.enums.CarDomain;
import com.thirdwheel.carbase.service.model.CarModel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ChassisCarsModelService extends AbstractCarsModelService {
    private final ChassisService chassisService;

    public ChassisCarsModelService(AbstractCarsModelService nextModelOfCarService, ChassisService chassisService) {
        super(nextModelOfCarService);
        this.chassisService = chassisService;
    }

    @Override
    public Map<String, CarModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Map<String, CarModel> modelOfCarByVendorAndText = super.getByVendorAndNameBeginning(vendorId, nameBeginning);
        List<Chassis> modelByVendor = chassisService.getByVendor(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new CarModel(x.getId(), x.getName(), CarDomain.CHASSIS));
        });
        return modelOfCarByVendorAndText;
    }

    @Override
    public List<CarModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarModel> byVendorAndCarModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        List<Chassis> modelByVendor = chassisService.getByVendorAndCarsModel(vendorId, carsModelName);
        modelByVendor.forEach(x -> {
            byVendorAndCarModelAndYear.add(new CarModel(x.getId(), x.getName(), CarDomain.CHASSIS));
        });
        return byVendorAndCarModelAndYear;
    }
}
