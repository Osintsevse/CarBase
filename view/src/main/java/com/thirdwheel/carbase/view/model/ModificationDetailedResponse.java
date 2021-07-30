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
    private final LocalDate manufacturingStartDate;
    private final LocalDate manufacturingEndDate;
    private final WDType wdType;
    private final TransmissionType transmissionType;
    private final EngineResponse engine;
    private final Long length;
    private final Long width;
    private final Long wheelBase;
    private final Long frontTrack;
    private final Long rearTrack;
    private final Double acceleration0100;
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
        this.manufacturingStartDate = modification.getStart();
        this.manufacturingEndDate = modification.getEnd();
        this.wdType = modification.getWdType();
        this.transmissionType = modification.getTransmissionType();
        this.engine = new EngineResponse(modification.getEngineModification());
        this.length = modification.getChassis().getLength();
        this.width = modification.getChassis().getWidth();
        this.wheelBase = modification.getChassis().getWheelBase();
        this.frontTrack = modification.getChassis().getFrontTrack();
        this.rearTrack = modification.getChassis().getRearTrack();
        this.acceleration0100 = modification.getAcceleration0100();
        this.squarenessCoefficient = modification.getChassis().getSquarenessCoefficient();
    }
}
