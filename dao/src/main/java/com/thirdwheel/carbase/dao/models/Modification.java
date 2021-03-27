package com.thirdwheel.carbase.dao.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;



@Data
@NoArgsConstructor
@Entity
@Table(name = "modifications")
public class Modification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "modification_id", nullable = false)
    private int modificationId;

    @Column(name = "modification_name", nullable = false)
    private String modificationName;

    @Column(name = "steering_wheel_position", nullable = false)
    private String steeringWheelPosition;

    @Column(name = "transmission_type", nullable = false)
    private TransmissionTypes transmissionType;

    @Column(name = "start")
    private Timestamp start;

    @Column(name = "end")
    private Timestamp end;

    @Column(name = "acceleration0100")
    private double acceleration0100;

    @Column(name = "front_wheels")
    private String frontWheels;

    @Column(name = "rear_wheels")
    private String rearWheels;

    @Column(name = "gear_count")
    private int gearCount;

    @Column(name = "mtr")
    private double mtr;

    @Column(name = "seat_count")
    private int seatCount;

    @Column(name = "seat_row_count")
    private int seatRowCount;

    @Column(name = "wd_type", nullable = false)
    private WDTypes wdType;

    @Column(name = "clearance")
    private long clearance;

    @Column(name = "country_build")
    private String countryBuild;

    @Column(name = "country_store")
    private String countryStore;

    @Column(name = "door_count")
    private int doorCount;

    @Column(name = "weight")
    private int weight;

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
    @JoinColumn(name = "chassis_id")
    private Chassis chassis;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_id")
    private Engine engine;
}
