package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.service.enums.CarsModelsType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CarsModelForResponse {
    private final String name;
}