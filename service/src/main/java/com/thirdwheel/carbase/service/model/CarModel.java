package com.thirdwheel.carbase.service.model;

import com.thirdwheel.carbase.service.enums.CarDomain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CarModel {
    private final int id;
    private final String name;
    private final CarDomain carDomain;
}
