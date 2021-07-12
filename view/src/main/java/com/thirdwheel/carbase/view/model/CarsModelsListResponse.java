package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.service.model.ModelOfCar;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
public class CarsModelsListResponse {
    private final List<CarsModelForResponse> entities;

    public CarsModelsListResponse(Map<String, ModelOfCar> byVendorAndText) {
        this.entities = new LinkedList<>();
        byVendorAndText.forEach((x, y) -> {
            this.entities.add(
                    new CarsModelForResponse(y.getName()));
        });
    }
}
