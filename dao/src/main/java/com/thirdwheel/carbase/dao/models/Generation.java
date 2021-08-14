package com.thirdwheel.carbase.dao.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "generations")
@ToString
@EqualsAndHashCode
@FieldNameConstants
public class Generation implements EntityWithIdAndName {
    @EqualsAndHashCode.Exclude
    @Id
    @SequenceGenerator(name = "generations_pk_sequence", sequenceName = "generations_pk_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generations_pk_sequence")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "\"end\"")
    private LocalDate end;

    @EqualsAndHashCode.Exclude
    @Column(name = "image_src")
    private String imageSrc;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Model model;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "generation", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Chassis> chassises;
}
