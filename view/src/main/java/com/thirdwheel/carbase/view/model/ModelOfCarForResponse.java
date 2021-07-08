package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.service.enums.TypeOfModelOfCar;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ModelOfCarForResponse {
    private final int id;
    private final String name;
    private final TypeOfModelOfCar typeOfModelOfCar;
}