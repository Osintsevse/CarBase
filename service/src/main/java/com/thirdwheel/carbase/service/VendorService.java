package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.Vendor;
import com.thirdwheel.carbase.dao.repositories.GeneralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendorService {
    private final GeneralRepository<Vendor> repository;

    @Transactional
    public void save(String name) {
        final Vendor vendor = new Vendor();
        vendor.setName(name);
        repository.save(vendor);
    }

    public Vendor getBiId(int id) {
        return repository.getById(id);
    }

    public List<Vendor> getAllVendors() {
        return repository.getAll();
    }
}
