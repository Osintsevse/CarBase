package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.enums.EngineType;
import com.thirdwheel.carbase.dao.models.enums.FuelType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "engines")
@ToString
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "cylinder_count")
    private Integer cylinderCount;

    @Column(name = "valve_count")
    private Integer valveCount;

    @Column(name = "valve_per_cylinder")
    private Integer valvePerCylinder;

    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type", nullable = false)
    private EngineType engineType;

    @Column(name = "bore")
    private Double bore;

    @Column(name = "stroke")
    private Double stroke;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
}
