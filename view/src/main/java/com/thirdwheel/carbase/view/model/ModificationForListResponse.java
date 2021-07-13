package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.Modification;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ModificationForListResponse {
    private final int id;
    private final String modificationName;
    private final String chassisName;
    private final String generationName;
    private final String modelName;
    private final String vendorName;
    private final String wholeName;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public ModificationForListResponse(Modification modification) {
        this.id = modification.getId();
        this.modificationName = modification.getName();
        this.chassisName = modification.getChassis().getName();
        this.generationName = modification.getChassis().getGeneration().getName();
        this.modelName = modification.getChassis().getGeneration().getModel().getName();
        this.vendorName = modification.getChassis().getGeneration().getModel().getVendor().getName();
        this.wholeName = this.vendorName + ", " + this.modelName + ", " + this.generationName + ", " + this.chassisName
                + ", " + this.modificationName;
        this.startDate = modification.getStart();
        this.endDate = modification.getEnd();
    }
}
