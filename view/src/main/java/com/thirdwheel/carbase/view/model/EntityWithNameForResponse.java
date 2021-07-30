package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.IEntityWithName;
import lombok.Getter;

@Getter
public class EntityWithNameForResponse {
    private final int id;
    private final String name;

    public EntityWithNameForResponse(IEntityWithName entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
