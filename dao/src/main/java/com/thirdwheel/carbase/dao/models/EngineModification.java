package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.enums.ChargerType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "engine_modifications")
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

    @Column(name = "charger_type")
    private ChargerType chargerType;

    @Column(name = "compression_ratio")
    private Integer compressionRatio;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_id")
    private Engine engine;
}
