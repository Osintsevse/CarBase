package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "generations")
@ToString(exclude = "chassises")
@EqualsAndHashCode
public class Generation implements IEntity {
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
    private List<Chassis> chassises;
}
