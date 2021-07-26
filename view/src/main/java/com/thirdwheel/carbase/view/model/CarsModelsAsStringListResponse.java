package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.service.model.CarModel;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class CarsModelsAsStringListResponse {
    private final List<String> carsModels;

    public CarsModelsAsStringListResponse(Map<String, CarModel> byVendorAndText) {
        carsModels = byVendorAndText.values().stream().map(CarModel::getName).collect(Collectors.toList());
    }
}
