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

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()){
            return false;
        } else {
            Suspension suspension = (Suspension) obj;
            return this.getName().equals(suspension.getName());
        }
    }
}
