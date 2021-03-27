package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.repositories.VendorRepository;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;

@AllArgsConstructor
public class VendorService {
    private final VendorRepository repository;

    @Transactional
    public void saveVendor(String name){
        final Vendor vendor = new Vendor();
        vendor.setVendorName(name);
        repository.saveVendor(vendor);
    }

    public Vendor getVendor(int id){
        return repository.getById(id);
    }
}
