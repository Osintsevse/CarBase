package com.thirdwheel.carbase.dao.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "suspensions")
public class Suspension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

}
