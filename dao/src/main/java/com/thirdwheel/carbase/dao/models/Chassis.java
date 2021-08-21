package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.enums.BodyStyle;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chassises")
@ToString
@EqualsAndHashCode
@FieldNameConstants
public class Chassis implements EntityWithIdAndName {
    @EqualsAndHashCode.Exclude
    @Id
    @SequenceGenerator(name = "chassises_pk_sequence", sequenceName = "chassises_pk_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chassises_pk_sequence")
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

    @Formula("wheel_base::float / greatest(front_track,rear_track)")
    private Double squarenessCoefficient;

    @Column(name = "height")
    private Long height;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_style")
    private BodyStyle bodyStyle;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id")
    @ToString.Exclude
    private Generation generation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "front_suspension_id")
    @ToString.Exclude
    private Suspension frontSuspension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rear_suspension_id")
    @ToString.Exclude
    private Suspension rearSuspension;
}

