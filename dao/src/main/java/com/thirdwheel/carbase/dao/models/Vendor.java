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
    @SequenceGenerator(name = "vendors_pk_sequence", sequenceName = "vendors_pk_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendors_pk_sequence")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
    private List<Model> models;

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
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
