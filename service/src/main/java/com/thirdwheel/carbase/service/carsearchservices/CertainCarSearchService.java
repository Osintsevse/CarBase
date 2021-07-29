package com.thirdwheel.carbase.service.carsearchservices;

import com.thirdwheel.carbase.dao.models.EntityWithIdAndName;
import com.thirdwheel.carbase.dao.models.enums.CarSearchDomain;
import com.thirdwheel.carbase.dao.repositories.RepositoryWithGettingByVendor;
import com.thirdwheel.carbase.service.model.CarSearchResponseElement;

import java.util.List;

public class CertainCarSearchService
        extends AbstractCarSearchService {
    private final RepositoryWithGettingByVendor<? extends EntityWithIdAndName> repository;
    private final CarSearchDomain carSearchDomain;

    public CertainCarSearchService(AbstractCarSearchService nextModelOfCarService,
                                   RepositoryWithGettingByVendor<? extends EntityWithIdAndName> repository,
                                   CarSearchDomain carSearchDomain) {
        super(nextModelOfCarService);
        this.repository = repository;
        this.carSearchDomain = carSearchDomain;
    }

    @Override
    public List<CarSearchResponseElement> getByVendorAndNameSubstring(int vendorId, String nameSubstring) {
        List<CarSearchResponseElement> carSearchResponseElements =
                super.getByVendorAndNameSubstring(vendorId, nameSubstring);

        List<? extends EntityWithIdAndName> ts = repository.getByVendorAndNameSubstringDistinctByName(vendorId, nameSubstring);

        ts.forEach(x -> carSearchResponseElements.add(new CarSearchResponseElement(x, carSearchDomain)));

        return carSearchResponseElements;
    }

}
