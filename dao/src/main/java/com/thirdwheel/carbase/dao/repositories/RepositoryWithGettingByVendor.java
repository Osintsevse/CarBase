package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.EntityWithIdAndName;

import java.util.List;

public interface RepositoryWithGettingByVendor<T extends EntityWithIdAndName> {
    List<T> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring);
}
