package com.thirdwheel.carbase.service.model;

import com.thirdwheel.carbase.service.enums.CarsModelType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CarsModel {
    private final int id;
    private final String name;
    private final CarsModelType carsModelType;
}
