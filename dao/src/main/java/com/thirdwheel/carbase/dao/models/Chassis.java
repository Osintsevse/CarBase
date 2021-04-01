package com.thirdwheel.carbase.dao.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "chassises")
public class Chassis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_name_generated", nullable = false)
    private boolean isNameGenerated;

    @Column(name = "length")
    private Long length;

    @Column(name = "width")
    private Long width;

    @Column(name = "wheel_base")
    private Long wheelBase;

    @Column(name = "front_overhang")
    private Long frontOverhang;

    @Column(name = "rear_overhang")
    private Long rearOverhang;

    @Column(name = "front_track")
    private Long frontTrack;

    @Column(name = "rear_track")
    private Long rearTrack;

    @Column(name = "height")
    private Long height;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "generation_id")
    private Generation generation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "front_suspension_id")
    private Suspension frontSuspension;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rear_suspension_id")
    private Suspension rearSuspension;

    @OneToMany(mappedBy="chassis", fetch=FetchType.EAGER)
    private List<Modification> modifications;
}
