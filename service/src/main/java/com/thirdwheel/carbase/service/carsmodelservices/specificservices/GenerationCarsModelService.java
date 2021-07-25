package com.thirdwheel.carbase.service.carsmodelservices.specificservices;

import com.thirdwheel.carbase.dao.models.Generation;
import com.thirdwheel.carbase.service.GenerationService;
import com.thirdwheel.carbase.service.carsmodelservices.AbstractCarsModelService;
import com.thirdwheel.carbase.service.enums.CarDomain;
import com.thirdwheel.carbase.service.model.CarModel;
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
    public Map<String, CarModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Map<String, CarModel> modelOfCarByVendorAndText = super.getByVendorAndNameBeginning(vendorId, nameBeginning);
        List<Generation> modelByVendor = generationService.getByVendor(vendorId, nameBeginning);
        modelByVendor.forEach(x -> {
            modelOfCarByVendorAndText.putIfAbsent(x.getName(), new CarModel(x.getId(), x.getName(), CarDomain.GENERATION));
        });
        return modelOfCarByVendorAndText;
    }

    @Override
    public List<CarModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarModel> byVendorAndCarModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        List<Generation> modelByVendor = generationService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        modelByVendor.forEach(x -> {
            byVendorAndCarModelAndYear.add(new CarModel(x.getId(), x.getName(), CarDomain.GENERATION));
        });
        return byVendorAndCarModelAndYear;
    }
}
