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

    public boolean equals(Vendor vendor) {
        if (
                (this.getName().equals(vendor.getName()))
        ) return true;
        else return false;
    }
}
