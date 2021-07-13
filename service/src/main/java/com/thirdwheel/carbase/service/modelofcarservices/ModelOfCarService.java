package com.thirdwheel.carbase.service.modelofcarservices;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.models.enums.SearchFieldForVendor;
import com.thirdwheel.carbase.service.GeneralService;
import com.thirdwheel.carbase.service.model.CarsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ModelOfCarService implements IModelOfCarService {
    private final GeneralService<Vendor> vendorService;
    private final ModelOfCarServiceFabric modelOfCarServiceFabric;

    @Override
    public Map<String, CarsModel> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Vendor byId = vendorService.getById(vendorId);
        EnumSet<SearchFieldForVendor> fieldsForVendors =
                SearchFieldForVendor.fromInt(byId.getVendorsConfiguration().getSearchFieldsBitMask());
        IModelOfCarService carService = null;
        for (SearchFieldForVendor fieldsForVendor : fieldsForVendors) {
            carService = modelOfCarServiceFabric.getModelOfCarService(fieldsForVendor, carService);
        }
        if (carService == null) {
            return new TreeMap<>();
        } else {
            return carService.getByVendorAndNameBeginning(vendorId, nameBeginning);
        }
    }

    @Override
    public List<CarsModel> getByVendorAndCarsModelAndYear(int vendorId, String carsModelName, String year) {
        Vendor byId = vendorService.getById(vendorId);
        EnumSet<SearchFieldForVendor> fieldsForVendors =
                SearchFieldForVendor.fromInt(byId.getVendorsConfiguration().getSearchFieldsBitMask());
        IModelOfCarService carService = null;
        for (SearchFieldForVendor fieldsForVendor : fieldsForVendors) {
            carService = modelOfCarServiceFabric.getModelOfCarService(fieldsForVendor, carService);
        }
        if (carService == null) {
            return new ArrayList<>();
        } else {
            return carService.getByVendorAndCarsModelAndYear(vendorId, carsModelName, year);
        }
    }
}
