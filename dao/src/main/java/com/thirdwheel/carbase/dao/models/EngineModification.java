package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.enums.ChargerTypes;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "engine_modifications")
public class EngineModification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "engine_modification_id", nullable = false)
    private int engineModificationId;

    @Column(name = "engine_modification_name", nullable = false)
    private String engineModificationName;

    @Column(name = "max_power")
    private int maxPower;

    @Column(name = "max_torque")
    private int maxTorque;

    @Column(name = "max_power_engine_speed")
    private int maxPowerEngineSpeed;

    @Column(name = "max_torque_engine_speed")
    private int maxTorqueEngineSpeed;

    @Column(name = "charger_type")
    private ChargerTypes chargerType;

    @Column(name = "compression_ratio")
    private int compressionRatio;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @OneToMany(mappedBy="engine", fetch=FetchType.EAGER)
    private List<Modification> modifications;
}