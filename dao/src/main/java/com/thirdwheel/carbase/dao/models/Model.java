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
    @SequenceGenerator(name="models_pk_sequence",sequenceName="models_pk_sequence", allocationSize=500)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="models_pk_sequence")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
    private List<Generation> generations;

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()){
            return false;
        } else {
            Model model = (Model) obj;
            return (this.getName().equals(model.getName())) &&
                    (this.getVendor().equals(model.getVendor()));
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode()+vendor.hashCode();
    }
}
