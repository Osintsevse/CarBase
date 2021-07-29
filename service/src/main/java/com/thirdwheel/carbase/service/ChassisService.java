package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.repositories.ChassisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChassisService extends GeneralService<Chassis, ChassisRepository> {

    public ChassisService(ChassisRepository repository) {
        super(repository);
    }

    public List<Chassis> getByVendor(Integer vendorId) {
        return repository.getByVendor(vendorId);
    }

    public List<Chassis> getByVendorAndNameSubstring(Integer vendorId, String nameSubstring) {
        return repository.getByVendorAndNameSubstring(vendorId, nameSubstring);
    }

    public List<Chassis> getByVendorAndNameSubstringDistinctByName(Integer vendorId, String nameSubstring) {
        return repository.getByVendorAndNameSubstringDistinctByName(vendorId, nameSubstring);
    }

    public List<Chassis> getByVendorAndCarsModel(int vendorId, String carsModelName) {
        return repository.getByVendorAndCarsModel(vendorId, carsModelName);
    }

}
