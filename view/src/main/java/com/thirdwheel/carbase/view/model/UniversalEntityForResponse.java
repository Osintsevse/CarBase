package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.IEntity;
import lombok.Getter;

@Getter
public class UniversalEntityForResponse {
    private final int id;
    private final String name;

    public UniversalEntityForResponse(IEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
