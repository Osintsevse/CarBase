package com.thirdwheel.carbase.service.carsmodelservices;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.models.enums.SearchFieldForVendor;
import com.thirdwheel.carbase.dao.repositories.VendorRepository;
import com.thirdwheel.carbase.service.GeneralService;
import com.thirdwheel.carbase.service.model.CarModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CarsModelService {
    private final GeneralService<Vendor, VendorRepository> vendorService;
    private final CarsModelServiceFabric carsModelServiceFabric;

    public Map<String, CarModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Vendor byId = vendorService.getById(vendorId);
        EnumSet<SearchFieldForVendor> fieldsForVendors =
                SearchFieldForVendor.fromInt(byId.getVendorsConfiguration().getSearchFieldsBitMask());

        AbstractCarsModelService carService = null;

        for (SearchFieldForVendor fieldsForVendor : fieldsForVendors) {
            carService = carsModelServiceFabric.getCarsModelService(fieldsForVendor, carService);
        }

        if (carService == null) {
            return new TreeMap<>();
        } else {
            return carService.getByVendorAndNameBeginning(vendorId, nameBeginning);
        }
    }

    public List<CarModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
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
