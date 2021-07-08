package com.thirdwheel.carbase.service.model;

import com.thirdwheel.carbase.service.enums.TypeOfModelOfCar;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ModelOfCar {
    private final int id;
    private final String name;
    private final TypeOfModelOfCar typeOfModelOfCar;
}
