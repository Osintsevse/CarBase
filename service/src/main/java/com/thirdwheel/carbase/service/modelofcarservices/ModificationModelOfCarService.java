package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.service.enums.CarsModelsType;
import com.thirdwheel.carbase.service.model.CarsModel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ModificationModelOfCarService extends AModelOfCarService {
    private final ModificationService modificationService;

    public ModificationModelOfCarService(IModelOfCarService nextModelOfCarService, ModificationService modificationService) {
        super(nextModelOfCarService);
        this.modificationService = modificationService;
    }

    @Override
    public Map<String, CarsModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Map<String, CarsModel> modelOfCarByVendorAndText = super.getByVendorAndNameBeginning(vendorId, nameBeginning);
        List<Modification> modelByVendor = modificationService.getByVendorAndNameBeginning(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new CarsModel(x.getId(), x.getName(), CarsModelsType.MODIFICATION));
        });
        return modelOfCarByVendorAndText;
    }

    @Override
    public List<CarsModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarsModel> byVendorAndCarsModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        List<Modification> modelByVendor = modificationService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        modelByVendor.forEach(x -> {
            byVendorAndCarsModelAndYear.add(new CarsModel(x.getId(), x.getName(), CarsModelsType.MODIFICATION));
        });
        return byVendorAndCarsModelAndYear;
    }
}
