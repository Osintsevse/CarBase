package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.EngineModification;
import lombok.Getter;

@Getter
public class EngineResponse {
    private final int id;
    private final String name;

    public EngineResponse(EngineModification engineModification) {
        this.id = engineModification.getId();
        this.name = engineModification.getName();
    }
}
