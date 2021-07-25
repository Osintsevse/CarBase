package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Data
@Entity
@Table(name = "suspensions")
@ToString
@EqualsAndHashCode
@FieldNameConstants
public class Suspension implements IEntityWithName {
    @EqualsAndHashCode.Exclude
    @Id
    @SequenceGenerator(name = "suspensions_pk_sequence", sequenceName = "suspensions_pk_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suspensions_pk_sequence")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
}
