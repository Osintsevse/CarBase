package com.thirdwheel.carbase.view.model;

import com.thirdwheel.carbase.dao.models.EngineModification;
import com.thirdwheel.carbase.dao.models.enums.ChargerType;
import com.thirdwheel.carbase.dao.models.enums.EngineType;
import com.thirdwheel.carbase.dao.models.enums.FuelType;
import lombok.Getter;

@Getter
public class EngineResponse {
    private final int id;
    private final String name;
    private final Integer maxPower;
    private final Integer maxTorque;
    private final ChargerType chargerType;
    private final FuelType fuelType;
    private final Integer volume;
    private final Integer cylinderCount;
    private final EngineType engineType;
    private final Double bore;
    private final Double stroke;
    private final String vendorName;


    public EngineResponse(EngineModification engineModification) {
        this.id = engineModification.getId();
        this.name = engineModification.getName();
        this.maxPower = engineModification.getMaxPower();
        this.maxTorque = engineModification.getMaxTorque();
        this.chargerType = engineModification.getChargerType();
        this.fuelType = engineModification.getEngine().getFuelType();
        this.volume = engineModification.getEngine().getVolume();
        this.cylinderCount = engineModification.getEngine().getCylinderCount();
        this.engineType = engineModification.getEngine().getEngineType();
        this.bore = engineModification.getEngine().getBore();
        this.stroke = engineModification.getEngine().getStroke();
        this.vendorName = engineModification.getEngine().getVendor().getName();
    }
}
