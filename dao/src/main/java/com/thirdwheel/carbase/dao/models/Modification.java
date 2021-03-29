package com.thirdwheel.carbase.dao.models;


import com.thirdwheel.carbase.dao.models.enums.TransmissionType;
import com.thirdwheel.carbase.dao.models.enums.WDType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "steering_wheel_position", nullable = false)
    private String steeringWheelPosition;

    @Column(name = "transmission_type", nullable = false)
    private TransmissionType transmissionType;

    @Column(name = "start")
    private Timestamp start;

    @Column(name = "end")
    private Timestamp end;

    @Column(name = "acceleration0100")
    private Double acceleration0100;

    @Column(name = "front_wheels")
    private String frontWheels;

    @Column(name = "rear_wheels")
    private String rearWheels;

    @Column(name = "gear_count")
    private Integer gearCount;

    @Column(name = "mtr")
    private Double mtr;

    @Column(name = "seat_count")
    private Integer seatCount;

    @Column(name = "seat_row_count")
    private Integer seatRowCount;

    @Column(name = "wd_type", nullable = false)
    private WDType wdType;

    @Column(name = "clearance")
    private Long clearance;

    @Column(name = "country_build")
    private String countryBuild;

    @Column(name = "country_store")
    private String countryStore;

    @Column(name = "door_count")
    private Integer doorCount;

    @Column(name = "weight")
    private Integer weight;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "chassis_id")
    private Chassis chassis;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_modification_id")
    private EngineModification engineModification;
}
