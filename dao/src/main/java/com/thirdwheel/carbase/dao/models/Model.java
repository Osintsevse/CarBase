package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "model_id")
    private int modelId;
    @Column(name = "model_name")
    private String modelName;

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @OneToMany(mappedBy="model", fetch=FetchType.EAGER)
    private List<Generation> generations;
}
