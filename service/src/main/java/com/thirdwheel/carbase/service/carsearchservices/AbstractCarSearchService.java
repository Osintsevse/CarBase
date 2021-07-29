package com.thirdwheel.carbase.service.carsearchservices;

import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractCarSearchService {
    private AbstractCarSearchService nextCarsModelService;

    public List<CarSearchResponseElement> getByVendorAndNameSubstring(int vendorId, String nameSubstring) {
        if (nextCarsModelService != null) {
            return nextCarsModelService.getByVendorAndNameSubstring(vendorId, nameSubstring);
        } else {
            return new ArrayList<>();
        }
    }
}
