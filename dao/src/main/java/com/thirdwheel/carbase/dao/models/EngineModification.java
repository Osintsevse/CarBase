package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.common.NullOrEquals;
import com.thirdwheel.carbase.dao.models.enums.ChargerType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "engine_modifications")
@ToString
public class EngineModification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "max_power")
    private Integer maxPower;

    @Column(name = "max_torque")
    private Integer maxTorque;

    @Column(name = "max_power_rpm")
    private Integer maxPowerRPM;

    @Column(name = "max_torque_rpm")
    private Integer maxTorqueRPM;

    @Enumerated(EnumType.STRING)
    @Column(name = "charger_type")
    private ChargerType chargerType;

    @Column(name = "compression_ratio")
    private Double compressionRatio;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    public boolean equals(EngineModification engineModification) {
        if (
                (this.getName().equals(engineModification.getName())) &&
                        (this.getChargerType() == engineModification.getChargerType()) &&
                        (NullOrEquals.compare(this.getMaxPower(), engineModification.getMaxPower())) &&
                        (NullOrEquals.compare(this.getMaxPowerRPM(), engineModification.getMaxPowerRPM())) &&
                        (NullOrEquals.compare(this.getMaxTorque(), engineModification.getMaxTorque())) &&
                        (NullOrEquals.compare(this.getMaxTorqueRPM(), engineModification.getMaxTorqueRPM())) &&
                        (NullOrEquals.compare(this.getCompressionRatio(), engineModification.getCompressionRatio()))
        ) return true;
        else return false;
    }
}
