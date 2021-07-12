package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.enums.SearchFieldForVendor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.EnumSet;

@Data
@Entity
@Table(name = "vendors_configurations")
@EqualsAndHashCode
public class VendorsConfiguration implements IEntity {
    @EqualsAndHashCode.Exclude
    @Id
    @SequenceGenerator(name = "vendors_configurations_pk_sequence", sequenceName = "vendors_configurations_pk_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendors_configurations_pk_sequence")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "search_fields_bit_mask", nullable = false)
    private int searchFieldsBitMask;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor;

    @Override
    public String toString() {
        EnumSet<SearchFieldForVendor> searchFieldsForVendor = SearchFieldForVendor.fromInt(searchFieldsBitMask);
        StringBuilder stringBuilderFoFields = new StringBuilder();
        searchFieldsForVendor.forEach(x -> {
            stringBuilderFoFields.append(x);
            stringBuilderFoFields.append(",");
        });
        return "VendorsConfiguration{" +
                "id=" + id +
                ", vendor=" + vendor +
                ", searchFieldsBitMask=" + searchFieldsBitMask +
                ", searchFields{" + stringBuilderFoFields + "}" +
                '}';
    }
}
