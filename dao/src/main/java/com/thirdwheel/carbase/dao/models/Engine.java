package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.enums.EngineTypes;
import com.thirdwheel.carbase.dao.models.enums.FuelTypes;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "engines")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

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

    @Column(name = "engine_type", nullable = false)
    private EngineTypes engineType;

    @Column(name = "bore")
    private int bore;

    @Column(name = "stroke")
    private int stroke;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(mappedBy="engine", fetch=FetchType.EAGER)
    private List<EngineModification> engineModifications;
}
