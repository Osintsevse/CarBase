package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "vendor_id")
    private int vendorId;
    @Column(name = "vendor_name")
    private String vendorName;

    @OneToMany(mappedBy="vendor", fetch=FetchType.EAGER)
    private List<Model> models;

    @OneToMany(mappedBy="vendor", fetch=FetchType.EAGER)
    private List<Engine> engines;
}
