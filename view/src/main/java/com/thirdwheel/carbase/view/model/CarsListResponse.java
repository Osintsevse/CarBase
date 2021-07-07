package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.Modification;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class CarsListResponse {
    private final List<CarForListResponse> entities;

    public CarsListResponse(List<? extends Modification> daoEntities) {
        entities = new LinkedList<>();
        daoEntities.forEach(x ->
                entities.add(new CarForListResponse(x)));
    }
}
