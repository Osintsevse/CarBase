package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.IEntityWithName;
import com.thirdwheel.carbase.dao.repositories.GeneralEntityWithNameRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GeneralService<T extends IEntityWithName> {
    protected final GeneralEntityWithNameRepository<T> repository;

    public T getBiId(int id) {
        return repository.getById(id);
    }

    public List<T> getByNameBeginning(String nameBeginning) {
        if (nameBeginning == null) {
            return repository.getAll();
        } else {
            return repository.getByNameBeginning(nameBeginning);
        }
    }

    public List<T> getAll() {
        return repository.getAll();
    }
}
