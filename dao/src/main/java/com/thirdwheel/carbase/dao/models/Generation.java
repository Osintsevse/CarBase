package com.thirdwheel.carbase.dao.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "generations")
public class Generation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private Timestamp start;
    private Timestamp end;
    @Column(name = "image_src")
    private String imageSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    public Generation() {
    }

    public Generation(String name, Timestamp start, Timestamp end, String imageSrc, Model model) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.imageSrc = imageSrc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public String toString(){
        return id + " "+name;
    }
}
