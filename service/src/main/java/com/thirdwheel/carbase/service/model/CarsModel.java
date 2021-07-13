package com.thirdwheel.carbase.service.model;

import com.thirdwheel.carbase.service.enums.CarsModelsType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CarsModel {
    private final int id;
    private final String name;
    private final CarsModelsType carsModelsType;
}
