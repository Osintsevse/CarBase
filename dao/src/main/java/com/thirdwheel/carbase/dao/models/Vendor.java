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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendor_seq")
    @SequenceGenerator(name = "vendor_seq", sequenceName = "vendor_seq", allocationSize = 1)
    @Column(name = "vendor_id", nullable = false)
    private int vendorId;

    @Column(name = "vendor_name", nullable = false)
    private String vendorName;

    @OneToMany(mappedBy="vendor", fetch=FetchType.EAGER)
    private List<Model> models;

    @OneToMany(mappedBy="vendor", fetch=FetchType.EAGER)
    private List<Engine> engines;
}
