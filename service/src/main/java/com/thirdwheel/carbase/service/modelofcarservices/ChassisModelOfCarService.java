package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.service.ChassisService;
import com.thirdwheel.carbase.service.enums.TypeOfModelOfCar;
import com.thirdwheel.carbase.service.model.ModelOfCar;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ChassisModelOfCarService extends AModelOfCarService {
    private final ChassisService chassisService;

    public ChassisModelOfCarService(IModelOfCarService nextModelOfCarService, ChassisService chassisService) {
        super(nextModelOfCarService);
        this.chassisService = chassisService;
    }

    @Override
    public Map<String, ModelOfCar> getByVendorAndText(int vendorId, String nameBeginning) {
        Map<String, ModelOfCar> modelOfCarByVendorAndText = super.getByVendorAndText(vendorId, nameBeginning);
        List<Chassis> modelByVendor = chassisService.getByVendor(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new ModelOfCar(x.getId(), x.getName(), TypeOfModelOfCar.CHASSSIS));
        });
        return modelOfCarByVendorAndText;
    }
}
