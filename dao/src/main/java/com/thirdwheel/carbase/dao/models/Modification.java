package com.thirdwheel.carbase.dao.models;


import com.thirdwheel.carbase.dao.models.common.BothNullOrEquals;
import com.thirdwheel.carbase.dao.models.enums.SteeringWheelPosition;
import com.thirdwheel.carbase.dao.models.enums.TransmissionType;
import com.thirdwheel.carbase.dao.models.enums.WDType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "modifications")
@ToString
public class Modification {
    @Id
    @SequenceGenerator(name="modifications_pk_sequence",sequenceName="modifications_pk_sequence", allocationSize=500)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="modifications_pk_sequence")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "steering_wheel_position", nullable = false)
    private SteeringWheelPosition steeringWheelPosition;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission_type", nullable = false)
    private TransmissionType transmissionType;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "\"end\"")
    private LocalDate end;

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

    @Enumerated(EnumType.STRING)
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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "chassis_id")
    private Chassis chassis;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_modification_id")
    private EngineModification engineModification;

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()){
            return false;
        } else {
            Modification modification = (Modification) obj;
            return (this.getName().equals(modification.getName())) &&
                    (this.getEngineModification().equals(modification.getEngineModification())) &&
                    (this.getSteeringWheelPosition() == modification.getSteeringWheelPosition()) &&
                    (this.getTransmissionType() == modification.getTransmissionType()) &&
                    (this.getWdType() == modification.getWdType()) &&
                    (this.getChassis().equals(modification.getChassis())) &&
                    (BothNullOrEquals.compare(this.getStart(), modification.getStart())) &&
                    (BothNullOrEquals.compare(this.getEnd(), modification.getEnd())) &&
                    (BothNullOrEquals.compare(this.getAcceleration0100(), modification.getAcceleration0100())) &&
                    (BothNullOrEquals.compare(this.getFrontWheels(), modification.getFrontWheels())) &&
                    (BothNullOrEquals.compare(this.getRearWheels(), modification.getRearWheels())) &&
                    (BothNullOrEquals.compare(this.getGearCount(), modification.getGearCount())) &&
                    (BothNullOrEquals.compare(this.getMtr(), modification.getMtr())) &&
                    (BothNullOrEquals.compare(this.getSeatCount(), modification.getSeatCount())) &&
                    (BothNullOrEquals.compare(this.getSeatRowCount(), modification.getSeatRowCount())) &&
                    (BothNullOrEquals.compare(this.getClearance(), modification.getClearance())) &&
                    (BothNullOrEquals.compare(this.getCountryBuild(), modification.getCountryBuild())) &&
                    (BothNullOrEquals.compare(this.getCountryStore(), modification.getCountryStore())) &&
                    (BothNullOrEquals.compare(this.getDoorCount(), modification.getDoorCount())) &&
                    (BothNullOrEquals.compare(this.getWeight(), modification.getWeight()));
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode()+transmissionType.ordinal();
    }
}
