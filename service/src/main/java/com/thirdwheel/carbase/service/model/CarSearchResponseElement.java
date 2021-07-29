package com.thirdwheel.carbase.service.model;

import com.thirdwheel.carbase.dao.models.EntityWithIdAndName;
import com.thirdwheel.carbase.dao.models.enums.CarSearchDomain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CarSearchResponseElement {
    private final EntityWithIdAndName entityWithName;
    private final CarSearchDomain carSearchDomain;
}
