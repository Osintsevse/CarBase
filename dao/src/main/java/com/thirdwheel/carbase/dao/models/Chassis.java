package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chassises")
public class Chassis {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "chassis_id", nullable = false)
    private int chassisId;

    @Column(name = "chassis_name", nullable = false)
    private String chassisName;

    @Column(name = "is_name_generated", nullable = false)
    private boolean isNameGenerated;

    @Column(name = "length")
    private long length;

    @Column(name = "width")
    private long width;

    @Column(name = "wheel_base")
    private long wheelBase;

    @Column(name = "front_overhang")
    private long frontOverhang;

    @Column(name = "rear_overhang")
    private long rearOverhang;

    @Column(name = "front_track")
    private long frontTrack;

    @Column(name = "rear_track")
    private long rearTrack;

    @Column(name = "height")
    private long height;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "front_suspension_id")
    private Suspension frontSuspension;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "rear_suspension_id")
    private Suspension rearSuspension;

    @OneToMany(mappedBy="chassis", fetch=FetchType.EAGER)
    private List<Modification> modifications;
}
