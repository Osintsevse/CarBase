package com.thirdwheel.carbase.service.carsmodelservices.specificservices;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.service.ModificationService;
import com.thirdwheel.carbase.service.carsmodelservices.AbstractCarsModelService;
import com.thirdwheel.carbase.service.enums.CarSearchDomain;
import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ModificationCarsModelService extends AbstractCarsModelService {
    private final ModificationService modificationService;

    public ModificationCarsModelService(AbstractCarsModelService nextModelOfCarService, ModificationService modificationService) {
        super(nextModelOfCarService);
        this.modificationService = modificationService;
    }

    @Override
    public List<CarSearchResponseElement> getByVendorAndNameSubstring(int vendorId, String nameSubstring) {
        List<CarSearchResponseElement> carSearchResponseElements =
                super.getByVendorAndNameSubstring(vendorId, nameSubstring);

        List<Modification> modifications =
                modificationService.getByVendorAndNameSubstringDistinctByName(vendorId, nameSubstring);

        modifications.forEach(x -> {
            carSearchResponseElements.add(new CarSearchResponseElement(x, CarSearchDomain.MODIFICATION));
        });

        return carSearchResponseElements;
    }

    @Override
    public List<CarSearchResponseElement> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        List<CarSearchResponseElement> byVendorAndCarModelAndYear = super.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);

        List<Modification> modelByVendor = modificationService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);

        modelByVendor.forEach(x -> {
            byVendorAndCarModelAndYear.add(new CarSearchResponseElement(x, CarSearchDomain.MODIFICATION));
        });

        return byVendorAndCarModelAndYear;
    }
}
