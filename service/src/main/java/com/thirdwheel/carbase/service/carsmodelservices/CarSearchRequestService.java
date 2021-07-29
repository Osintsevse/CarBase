package com.thirdwheel.carbase.service.carsmodelservices;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.models.enums.SearchFieldForVendor;
import com.thirdwheel.carbase.dao.repositories.VendorRepository;
import com.thirdwheel.carbase.service.GeneralService;
import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarSearchRequestService {
    private final GeneralService<Vendor, VendorRepository> vendorService;
    private final CarsModelServiceFabric carsModelServiceFabric;

    public List<CarSearchResponseElement> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Vendor byId = vendorService.getById(vendorId);

        EnumSet<SearchFieldForVendor> fieldsForVendors =
                SearchFieldForVendor.fromInt(byId.getVendorsConfiguration().getSearchFieldsBitMask());

        AbstractCarsModelService carService = null;

        for (SearchFieldForVendor fieldsForVendor : fieldsForVendors) {
            carService = carsModelServiceFabric.getCarsModelService(fieldsForVendor, carService);
        }

        if (carService == null) {
            return new ArrayList<>();
        } else {
            return carService.getByVendorAndNameSubstring(vendorId, nameBeginning);
        }
    }

    public List<CarSearchResponseElement> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        Vendor byId = vendorService.getById(vendorId);
        EnumSet<SearchFieldForVendor> fieldsForVendors =
                SearchFieldForVendor.fromInt(byId.getVendorsConfiguration().getSearchFieldsBitMask());

        AbstractCarsModelService carService = null;

        for (SearchFieldForVendor fieldsForVendor : fieldsForVendors) {
            carService = carsModelServiceFabric.getCarsModelService(fieldsForVendor, carService);
        }

        if (carService == null) {
            return new ArrayList<>();
        } else {
            return carService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        }
    }
}
