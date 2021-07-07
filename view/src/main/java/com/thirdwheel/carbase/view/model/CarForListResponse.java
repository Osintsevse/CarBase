package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.Modification;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CarForListResponse extends EntityWithNameForResponse {
    private final String vendorName;
    private final String modelName;
    private final String generationName;
    private final String chassisName;
    private final LocalDate start;
    private final LocalDate end;

    public CarForListResponse(Modification entity) {
        super(entity);
        vendorName = entity.getChassis().getGeneration().getModel().getVendor().getName();
        modelName = entity.getChassis().getGeneration().getModel().getName();
        generationName = entity.getChassis().getGeneration().getName();
        chassisName = entity.getChassis().getName();
        start = entity.getStart();
        end = entity.getEnd();
    }
}
