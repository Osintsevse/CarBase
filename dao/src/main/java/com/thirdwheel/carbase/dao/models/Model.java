package com.thirdwheel.carbase.dao.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "models")
@ToString
@EqualsAndHashCode
@FieldNameConstants
public class Model implements EntityWithIdAndName {
    @EqualsAndHashCode.Exclude
    @Id
    @SequenceGenerator(name = "models_pk_sequence", sequenceName = "models_pk_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "models_pk_sequence")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    @ToString.Exclude
    private Vendor vendor;
}
