package com.thirdwheel.carbase.service.carsmodelservices.specificservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.service.carsmodelservices.AbstractCarsModelService;
import com.thirdwheel.carbase.service.enums.CarDomain;
import com.thirdwheel.carbase.service.model.CarModel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ModificationCarsModelService extends AbstractCarsModelService {
    private final ModificationService modificationService;

    public ModificationCarsModelService(AbstractCarsModelService nextModelOfCarService, ModificationService modificationService) {
        super(nextModelOfCarService);
        this.modificationService = modificationService;
    }

    @Override
    public Map<String, CarModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Map<String, CarModel> modelOfCarByVendorAndText = super.getByVendorAndNameBeginning(vendorId, nameBeginning);
        List<Modification> modelByVendor = modificationService.getByVendorAndNameBeginning(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new CarModel(x.getId(), x.getName(), CarDomain.MODIFICATION));
        });
        return modelOfCarByVendorAndText;
    }

    @Override
    public List<CarModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarModel> byVendorAndCarModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        List<Modification> modelByVendor = modificationService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        modelByVendor.forEach(x -> {
            byVendorAndCarModelAndYear.add(new CarModel(x.getId(), x.getName(), CarDomain.MODIFICATION));
        });
        return byVendorAndCarModelAndYear;
    }
}
