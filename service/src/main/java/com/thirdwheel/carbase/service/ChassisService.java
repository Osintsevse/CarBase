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

    public List<Chassis> getByVendor(Integer vendorId, String nameBeginning) {
        if (nameBeginning == null) {
            return repository.getByVendor(vendorId);
        } else {
            return repository.getByVendorAndName(vendorId, nameBeginning);
        }
    }

    public List<Chassis> getByVendorAndCarsModel(int vendorId, String carsModelName) {
        return repository.getByVendorAndCarsModel(vendorId, carsModelName);
    }

}
