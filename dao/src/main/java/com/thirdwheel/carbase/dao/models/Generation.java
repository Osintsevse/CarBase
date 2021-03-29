package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "generations")
public class Generation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

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
