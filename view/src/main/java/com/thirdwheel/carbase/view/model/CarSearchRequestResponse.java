package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.service.enums.CarSearchDomain;
import com.thirdwheel.carbase.service.model.CarSearchResponseElement;
import com.thirdwheel.carbase.view.model.subelements.EntityWithNameForResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@RequiredArgsConstructor
public class CarSearchRequestResponse {
    private final List<? extends EntityWithNameForResponse> models;
    private final List<? extends EntityWithNameForResponse> generations;
    private final List<? extends EntityWithNameForResponse> chassises;
    private final List<? extends EntityWithNameForResponse> modifications;

    public CarSearchRequestResponse(List<CarSearchResponseElement> carsByVendorAndNameSubstring) {
        this.models = carsByVendorAndNameSubstring.stream()
                .filter(x -> x.getCarSearchDomain() == CarSearchDomain.MODEL)
                .map(x -> new EntityWithNameForResponse(x.getEntityWithName()))
                .collect(Collectors.toList());

        this.generations = carsByVendorAndNameSubstring.stream()
                .filter(x -> x.getCarSearchDomain() == CarSearchDomain.GENERATION)
                .map(x -> new EntityWithNameForResponse(x.getEntityWithName()))
                .collect(Collectors.toList());

        this.chassises = carsByVendorAndNameSubstring.stream()
                .filter(x -> x.getCarSearchDomain() == CarSearchDomain.CHASSIS)
                .map(x -> new EntityWithNameForResponse(x.getEntityWithName()))
                .collect(Collectors.toList());

        this.modifications = carsByVendorAndNameSubstring.stream()
                .filter(x -> x.getCarSearchDomain() == CarSearchDomain.MODIFICATION)
                .map(x -> new EntityWithNameForResponse(x.getEntityWithName()))
                .collect(Collectors.toList());
    }
}
