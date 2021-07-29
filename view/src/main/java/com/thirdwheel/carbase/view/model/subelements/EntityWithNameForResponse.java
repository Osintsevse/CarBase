package com.thirdwheel.carbase.view.model.subelements;

import com.thirdwheel.carbase.dao.models.EntityWithIdAndName;
import lombok.Getter;

@Getter
public class EntityWithNameForResponse {
    private final int id;
    private final String name;

    public EntityWithNameForResponse(EntityWithIdAndName entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
