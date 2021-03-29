package com.thirdwheel.carbase.dao.models;


import com.thirdwheel.carbase.dao.models.enums.TransmissionTypes;
import com.thirdwheel.carbase.dao.models.enums.WDTypes;
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
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

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

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "chassis_id")
    private Chassis chassis;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_modification_id")
    private EngineModification engineModification;
}
