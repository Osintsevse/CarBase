package com.thirdwheel.carbase.dao.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "generations")
public class Generation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "generation_id")
    private int generationId;
    @Column(name = "generation_name")
    private String generationName;
    @Column(name = "start")
    private Timestamp start;
    @Column(name = "end")
    private Timestamp end;
    @Column(name = "image_src")
    private String imageSrc;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToMany(mappedBy="generation", fetch=FetchType.EAGER)
    private List<Chassis> chassises;
}
