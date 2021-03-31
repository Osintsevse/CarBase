package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.repositories.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class VendorService {
    private final VendorRepository repository;

    @Transactional
    public void saveVendor(String name){
        final Vendor vendor = new Vendor();
        vendor.setName(name);
        repository.saveVendor(vendor);
    }

    public Vendor getVendor(int id){
        return repository.getById(id);
    }
}
