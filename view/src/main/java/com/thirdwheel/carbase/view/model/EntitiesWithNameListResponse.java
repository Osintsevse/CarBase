package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.IEntityWithName;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EntitiesWithNameListResponse {
    private final List<EntityWithNameForResponse> entities;

    public EntitiesWithNameListResponse(List<? extends IEntityWithName> entitiesInput) {
        entities = entitiesInput.stream().map(EntityWithNameForResponse::new).collect(Collectors.toList());
    }
}
