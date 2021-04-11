package com.thirdwheel.carbase.dao.models;

import com.thirdwheel.carbase.dao.models.common.NullOrEquals;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "generations")
@ToString(exclude = "chassises")
public class Generation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public boolean equals(Generation generation) {
        if (
                (this.getName().equals(generation.getName())) &&
                        (NullOrEquals.compare(this.getStart(), generation.getStart())) &&
                        (NullOrEquals.compare(this.getEnd(), generation.getEnd()))
        ) return true;
        else return false;
    }
}
