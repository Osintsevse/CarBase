package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "models")
@ToString(exclude = "generations")
@EqualsAndHashCode
@FieldNameConstants
public class Model implements IEntityWithName {
    @EqualsAndHashCode.Exclude
    @Id
    @SequenceGenerator(name = "models_pk_sequence", sequenceName = "models_pk_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "models_pk_sequence")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY)
    private List<Generation> generations;
}
