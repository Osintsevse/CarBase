package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.IEntity;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class EntitiesListResponse {
    private final List<UniversalEntityForResponse> entities;

    public EntitiesListResponse(List<? extends IEntity> daoEntities) {
        entities = new LinkedList<>();
        daoEntities.forEach(x ->
                entities.add(new UniversalEntityForResponse(x)));
    }
}
