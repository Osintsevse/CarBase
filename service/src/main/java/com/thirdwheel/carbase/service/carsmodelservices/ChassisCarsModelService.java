package com.thirdwheel.carbase.service.carsmodelservices;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.service.ChassisService;
import com.thirdwheel.carbase.service.enums.CarsModelType;
import com.thirdwheel.carbase.service.model.CarsModel;
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
    public Map<String, CarsModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Map<String, CarsModel> modelOfCarByVendorAndText = super.getByVendorAndNameBeginning(vendorId, nameBeginning);
        List<Chassis> modelByVendor = chassisService.getByVendor(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new CarsModel(x.getId(), x.getName(), CarsModelType.CHASSIS));
        });
        return modelOfCarByVendorAndText;
    }

    @Override
    public List<CarsModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarsModel> byVendorAndCarsModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        List<Chassis> modelByVendor = chassisService.getByVendorAndCarsModel(vendorId, carsModelName);
        modelByVendor.forEach(x -> {
            byVendorAndCarsModelAndYear.add(new CarsModel(x.getId(), x.getName(), CarsModelType.CHASSIS));
        });
        return byVendorAndCarsModelAndYear;
    }
}