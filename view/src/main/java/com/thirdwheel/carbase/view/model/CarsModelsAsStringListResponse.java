package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.service.model.CarsModel;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
public class CarsModelsAsStringListResponse {
    private final List<String> carsModels;

    public CarsModelsAsStringListResponse(Map<String, CarsModel> byVendorAndText) {
        this.carsModels = new LinkedList<>();
        byVendorAndText.forEach((x, y) -> {
            this.carsModels.add(
                    y.getName());
        });
    }
}
