package com.thirdwheel.carbase.service;

import com.thirdwheel.carbase.dao.excetions.CarbaseEntityNotFoundException;
import com.thirdwheel.carbase.dao.models.EntityWithIdAndName;
import com.thirdwheel.carbase.dao.repositories.EntityWithIdRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GeneralService<T extends EntityWithIdAndName, R extends EntityWithIdRepository<T>> {
    protected final R repository;

    public T getById(int id) {
        T tEntity = repository.getById(id);
        if (tEntity == null) {
            throw new CarbaseEntityNotFoundException("Entity not found for id: " + id);
        }
        return tEntity;
    }

    public List<T> getAll() {
        return repository.getAll();
    }
}
