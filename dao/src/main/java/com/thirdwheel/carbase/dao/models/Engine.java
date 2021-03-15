package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

enum FuelTypes{
    Gasoline,
    Diesel,
    Electric,
    Unknown
}

enum EngineTypes{
    R,
    V,
    W,
    Opposite,
    Rotor,
    unknown
}

@Data
@NoArgsConstructor
@Entity
@Table(name = "engines")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "engine_id")
    private int engineId;
    @Column(name = "engine_name")
    private String engineName;
    @Column(name = "fuel_type")
    private FuelTypes fuelType;
    @Column(name = "volume")
    private int volume;
    @Column(name = "cylinder_count")
    private int cylinderCount;
    @Column(name = "valve_count")
    private int valveCount;
    @Column(name = "valve_per_cylinder")
    private int valvePerCylinder;
    @Column(name = "engine_type")
    private EngineTypes engineType;
    @Column(name = "bore")
    private int bore;
    @Column(name = "stroke")
    private int stroke;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(mappedBy="engine", fetch=FetchType.EAGER)
    private List<Modification> modifications;
}
