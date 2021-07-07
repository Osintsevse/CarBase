package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.Modification;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CarResponse extends EntityWithNameForResponse {
    private final String vendorName;
    private final String modelName;
    private final String chassisName;
    private final LocalDate start;
    private final LocalDate end;

    public CarResponse(Modification entity) {
        super(entity);
        vendorName = entity.getChassis().getGeneration().getModel().getVendor().getName();
        modelName = entity.getChassis().getGeneration().getModel().getName();
        chassisName = entity.getChassis().getName();
        start = entity.getStart();
        end = entity.getEnd();
    }
}
