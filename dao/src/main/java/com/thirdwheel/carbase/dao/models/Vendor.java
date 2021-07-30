package com.thirdwheel.carbase.dao.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "vendors")
@ToString(exclude = "models")
@EqualsAndHashCode
@FieldNameConstants
public class Vendor implements IEntityWithName {
    @EqualsAndHashCode.Exclude
    @Id
    @SequenceGenerator(name = "vendors_pk_sequence", sequenceName = "vendors_pk_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendors_pk_sequence")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
    private List<Model> models;

    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "vendor")
    private VendorsConfiguration vendorsConfiguration;
}
