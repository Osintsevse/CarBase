package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.models.IEntityWithName;
import com.thirdwheel.carbase.dao.repositories.GeneralEntityRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GeneralService<T extends IEntityWithName, R extends GeneralEntityRepository<T>> {
    protected final R repository;

    public T getById(int id) {
        return repository.getById(id);
    }

//    public List<T> getByNameBeginning(String nameBeginning) {
//        if (nameBeginning == null) {
//            return repository.getAll();
//        } else {
//            return repository.getByNameBeginning(nameBeginning);
//        }
//    }

    public List<T> getAll() {
        return repository.getAll();
    }
}
