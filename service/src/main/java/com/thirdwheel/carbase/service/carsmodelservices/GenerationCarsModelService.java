package com.thirdwheel.carbase.service.carsmodelservices;

import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.service.GenerationService;
import com.thirdwheel.carbase.service.enums.CarsModelType;
import com.thirdwheel.carbase.service.model.CarsModel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class GenerationCarsModelService extends AbstractCarsModelService {
    private final GenerationService generationService;

    public GenerationCarsModelService(AbstractCarsModelService nextModelOfCarService, GenerationService generationService) {
        super(nextModelOfCarService);
        this.generationService = generationService;
    }

    @Override
    public Map<String, CarsModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Map<String, CarsModel> modelOfCarByVendorAndText = super.getByVendorAndNameBeginning(vendorId, nameBeginning);
        List<Generation> modelByVendor = generationService.getByVendor(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new CarsModel(x.getId(), x.getName(), CarsModelType.GENERATION));
        });
        return modelOfCarByVendorAndText;
    }

    @Override
    public List<CarsModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarsModel> byVendorAndCarsModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        List<Generation> modelByVendor = generationService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        modelByVendor.forEach(x -> {
            byVendorAndCarsModelAndYear.add(new CarsModel(x.getId(), x.getName(), CarsModelType.GENERATION));
        });
        return byVendorAndCarsModelAndYear;
    }
}
