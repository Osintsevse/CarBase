package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.IEntityWithName;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class EntitiesWithNameListResponse {
    private final List<EntityWithNameForResponse> entities;

    public EntitiesWithNameListResponse(List<? extends IEntityWithName> daoEntities) {
        entities = new LinkedList<>();
        daoEntities.forEach(x ->
                entities.add(new EntityWithNameForResponse(x)));
    }
}