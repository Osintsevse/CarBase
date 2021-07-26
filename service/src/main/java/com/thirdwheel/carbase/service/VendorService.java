package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService extends GeneralService<Vendor, VendorRepository> {
    public VendorService(VendorRepository repository) {
        super(repository);
    }

    public List<Vendor> getByNameBeginning(String nameBeginning) {
        if (nameBeginning == null) {
            return repository.getAll();
        } else {
            return repository.getByNameBeginning(nameBeginning);
        }
    }

}
