package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.service.model.ModelOfCar;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
public class ModelsOfCarsListResponse {
    private final List<ModelOfCarForResponse> entities;

    public ModelsOfCarsListResponse(Map<String, ModelOfCar> byVendorAndText) {
        this.entities = new LinkedList<>();
        byVendorAndText.forEach((x, y) -> {
            this.entities.add(
                    new ModelOfCarForResponse(y.getId(), y.getName(), y.getTypeOfModelOfCar()));
        });
    }
}
