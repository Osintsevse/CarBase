package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.common.NullOrEquals;
import com.thirdwheel.carbase.dao.models.enums.BodyStyle;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "chassises")
@ToString(exclude = "modifications")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "body_style")
    private BodyStyle bodyStyle;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "generation_id")
    private Generation generation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "front_suspension_id")
    private Suspension frontSuspension;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rear_suspension_id")
    private Suspension rearSuspension;

    @OneToMany(mappedBy = "chassis", fetch = FetchType.LAZY)
    private List<Modification> modifications;

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()){
            return false;
        } else {
            Chassis chassis = (Chassis) obj;
            return (this.getName().equals(chassis.getName())) &&
                    (this.isNameGenerated == chassis.isNameGenerated) &&
                    (NullOrEquals.compare(this.getLength(), chassis.getLength())) &&
                    (NullOrEquals.compare(this.getWidth(), chassis.getWidth())) &&
                    (NullOrEquals.compare(this.getWheelBase(), chassis.getWheelBase())) &&
                    (NullOrEquals.compare(this.getFrontOverhang(), chassis.getFrontOverhang())) &&
                    (NullOrEquals.compare(this.getRearOverhang(), chassis.getRearOverhang())) &&
                    (NullOrEquals.compare(this.getFrontTrack(), chassis.getFrontTrack())) &&
                    (NullOrEquals.compare(this.getRearTrack(), chassis.getRearTrack())) &&
                    (NullOrEquals.compare(this.getHeight(), chassis.getHeight())) &&
                    (this.getBodyStyle() == chassis.getBodyStyle()) &&
                    (this.getGeneration().equals(chassis.getGeneration())) &&
                    (NullOrEquals.compare(this.getFrontSuspension(),chassis.getFrontSuspension()) &&
                    (NullOrEquals.compare(this.getRearSuspension(),chassis.getRearSuspension())));
        }
    }
}

