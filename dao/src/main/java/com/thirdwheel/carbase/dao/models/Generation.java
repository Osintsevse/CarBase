package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.common.BothNullOrEquals;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "generations")
@ToString(exclude = "chassises")
public class Generation implements IEntity {
    @Id
    @SequenceGenerator(name = "generations_pk_sequence", sequenceName = "generations_pk_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generations_pk_sequence")
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "\"end\"")
    private LocalDate end;

    @Column(name = "image_src")
    private String imageSrc;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToMany(mappedBy = "generation", fetch = FetchType.LAZY)
    private List<Chassis> chassises;

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        } else {
            Generation generation = (Generation) obj;
            return (this.getName().equals(generation.getName())) &&
                    (BothNullOrEquals.compare(this.getStart(), generation.getStart())) &&
                    (this.getModel().equals(generation.getModel())) &&
                    (BothNullOrEquals.compare(this.getEnd(), generation.getEnd()));
        }
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
