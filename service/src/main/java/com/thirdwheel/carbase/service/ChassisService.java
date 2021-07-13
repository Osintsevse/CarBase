package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Chassis;
import com.thirdwheel.carbase.dao.repositories.ChassisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChassisService extends GeneralService<Chassis> {
    public ChassisService(ChassisRepository repository) {
        super(repository);
    }

    public List<Chassis> getByVendor(Integer vendorId, String nameBeginning) {
        if (nameBeginning == null) {
            return ((ChassisRepository) repository).getByVendor(vendorId);
        } else {
            return ((ChassisRepository) repository).getByVendorAndName(vendorId, nameBeginning);
        }
    }

    public List<Chassis> getByVendorAndCarsModel(int vendorId, String carsModelName) {
        return ((ChassisRepository) repository).getByVendorAndCarsModelAndYear(vendorId, carsModelName);
    }

}
