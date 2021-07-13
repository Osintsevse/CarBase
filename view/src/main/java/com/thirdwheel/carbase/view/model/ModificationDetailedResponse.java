package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.Modification;
import com.thirdwheel.carbase.dao.models.enums.TransmissionType;
import com.thirdwheel.carbase.dao.models.enums.WDType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ModificationDetailedResponse {
    private final int id;
    private final String modificationName;
    private final String chassisName;
    private final String generationName;
    private final String modelName;
    private final String vendorName;
    private final String wholeName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final WDType wdType;
    private final TransmissionType transmissionType;
    private final EngineResponse engineResponse;
    private final Long length;
    private final Long width;
    private final Long wheelBase;
    private final Long frontTrack;
    private final Long rearTrack;
    private final double squarenessCoefficient;


    public ModificationDetailedResponse(Modification modification) {
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
        this.wdType = modification.getWdType();
        this.transmissionType = modification.getTransmissionType();
        this.engineResponse = new EngineResponse(modification.getEngineModification());
        this.length = modification.getChassis().getLength();
        this.width = modification.getChassis().getWidth();
        this.wheelBase = modification.getChassis().getWheelBase();
        this.frontTrack = modification.getChassis().getFrontTrack();
        this.rearTrack = modification.getChassis().getRearTrack();
        if ((this.frontTrack != 0) || (this.rearTrack != 0)) {
            this.squarenessCoefficient = (double) this.wheelBase / (double) (Math.max(this.frontTrack, this.rearTrack));
        } else {
            this.squarenessCoefficient = 0;
        }
    }
}
