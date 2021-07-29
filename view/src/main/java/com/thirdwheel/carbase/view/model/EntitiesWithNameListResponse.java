package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.EntityWithIdAndName;
import com.thirdwheel.carbase.view.model.subelements.EntityWithNameForResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EntitiesWithNameListResponse {
    private final List<EntityWithNameForResponse> entities;

    public EntitiesWithNameListResponse(List<? extends EntityWithIdAndName> entitiesInput) {
        entities = entitiesInput.stream().map(EntityWithNameForResponse::new).collect(Collectors.toList());
    }
}
