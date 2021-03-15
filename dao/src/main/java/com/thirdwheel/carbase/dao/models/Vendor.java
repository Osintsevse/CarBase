package com.thirdwheel.carbase.dao.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "vendor_id")
    private int id;
    @Column(name = "vendor_name")
    private String name;

    @OneToMany(mappedBy="vendor", fetch=FetchType.EAGER)
    private List<Model> models;
}
