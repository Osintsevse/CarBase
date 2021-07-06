package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.repositories.GeneralRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GeneralService<T> {
    protected final GeneralRepository<T> repository;

    public T getBiId(int id) {
        return repository.getById(id);
    }

    public List<T> getByNameBeginnig(String nameBeginning) {
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
