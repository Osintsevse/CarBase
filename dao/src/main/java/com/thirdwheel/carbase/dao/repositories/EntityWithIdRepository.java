package com.thirdwheel.carbase.dao.repositories;

import com.thirdwheel.carbase.dao.models.EntityWithId;

import java.util.List;

public interface EntityWithIdRepository<T extends EntityWithId> {
    void save(T entity);

    T getById(int id);

    List<T> getAll();
}
