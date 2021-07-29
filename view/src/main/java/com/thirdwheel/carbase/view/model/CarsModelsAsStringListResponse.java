package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class CarsModelsAsStringListResponse {
    private final List<String> carsModels;

    public CarsModelsAsStringListResponse(Map<String, CarSearchResponseElement> byVendorAndText) {
        carsModels = byVendorAndText.values().stream()
                .map(x -> x.getEntityWithName().getName())
                .collect(Collectors.toList());
    }
}
