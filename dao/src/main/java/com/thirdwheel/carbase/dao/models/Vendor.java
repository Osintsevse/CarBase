package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "vendors")
@ToString(exclude = "models")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
    private List<Model> models;

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()){
            return false;
        } else {
            Vendor vendor = (Vendor) obj;
            return this.getName().equals(vendor.getName());
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
