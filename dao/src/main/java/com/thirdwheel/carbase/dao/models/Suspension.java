package com.thirdwheel.carbase.dao.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "suspensions")
public class Suspension {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "suspension_id")
    private int suspensionId;
    @Column(name = "suspension_name")
    private String suspensionName;

}
