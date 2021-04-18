package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.common.NullOrEquals;
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
    @SequenceGenerator(name="engines_pk_sequence",sequenceName="engines_pk_sequence", allocationSize=500)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="engines_pk_sequence")
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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()){
            return false;
        } else {
            Engine engine = (Engine) obj;
            return (this.getName().equals(engine.getName())) &&
                    (this.getFuelType() == engine.getFuelType()) &&
                    (this.getEngineType() == engine.getEngineType()) &&
                    (NullOrEquals.compare(this.getVolume(), engine.getVolume())) &&
                    (NullOrEquals.compare(this.getCylinderCount(), engine.getCylinderCount())) &&
                    (NullOrEquals.compare(this.getValveCount(), engine.getValveCount())) &&
                    (NullOrEquals.compare(this.getValvePerCylinder(), engine.getValvePerCylinder())) &&
                    (NullOrEquals.compare(this.getBore(), engine.getBore())) &&
                    (NullOrEquals.compare(this.getStroke(), engine.getStroke()));
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
