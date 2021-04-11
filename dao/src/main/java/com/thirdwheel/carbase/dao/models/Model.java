package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "models")
@ToString(exclude = "generations")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
    private List<Generation> generations;

    public boolean equals(Model model) {
        if (
                (this.getName().equals(model.getName())) &&
                        (this.getVendor() == model.getVendor())
        ) return true;
        else return false;
    }
}
