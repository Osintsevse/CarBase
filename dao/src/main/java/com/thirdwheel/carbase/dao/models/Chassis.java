package com.thirdwheel.carbase.dao.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Data
@Entity
@Table(name = "chassises")
public class Chassis {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "chassis_id")
    private int chassisId;
    @Column(name = "chassis_name")
    private String chassisName;
    @Column(name = "is_name_generated")
    private boolean isNameGenerated;
    @Column(name = "length")
    private BigInteger length;
    @Column(name = "width")
    private BigInteger width;
    @Column(name = "wheel_base")
    private BigInteger wheelBase;
    @Column(name = "front_overhang")
    private BigInteger frontOverhang;
    @Column(name = "rear_overhang")
    private BigInteger rearOverhang;
    @Column(name = "front_track")
    private BigInteger frontTrack;
    @Column(name = "rear_track")
    private BigInteger rearTrack;
    @Column(name = "height")
    private BigInteger height;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "front_suspension_id")
    private Suspension frontSuspension;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "rear_suspension_id")
    private Suspension rearSuspension;

    @OneToMany(mappedBy="chassis", fetch=FetchType.EAGER)
    private List<Modification> modifications;
}
