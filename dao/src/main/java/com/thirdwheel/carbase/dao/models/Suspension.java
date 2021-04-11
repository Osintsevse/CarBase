package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "suspensions")
@ToString
public class Suspension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    public boolean equals(Suspension suspension) {
        if (
                (this.getName().equals(suspension.getName()))
        ) return true;
        else return false;
    }
}
