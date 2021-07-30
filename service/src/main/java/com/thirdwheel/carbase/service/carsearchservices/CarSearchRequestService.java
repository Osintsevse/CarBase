package com.thirdwheel.carbase.service.carsearchservices;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.models.enums.CarSearchDomain;
import com.thirdwheel.carbase.service.VendorService;
import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarSearchRequestService {
    private final VendorService vendorService;
    private final CarSearchServiceFactory carSearchServiceFactory;

    public List<CarSearchResponseElement> getByVendorAndNameBeginning(int vendorId, String nameBeginning) {
        Vendor byId = vendorService.getById(vendorId);

        EnumSet<CarSearchDomain> fieldsForVendors =
                CarSearchDomain.fromInt(byId.getVendorsConfiguration().getSearchFieldsBitMask());

        AbstractCarSearchService carService = null;

        for (CarSearchDomain fieldsForVendor : fieldsForVendors) {
            carService = carSearchServiceFactory.getCarsModelService(fieldsForVendor, carService);
        }

        if (carService == null) {
            return new ArrayList<>();
        } else {
            return carService.getByVendorAndNameSubstring(vendorId, nameBeginning);
        }
    }
}
