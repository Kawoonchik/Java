package com.example.plantcare.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "diseases")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String treatment;

    @ManyToMany(mappedBy = "diseases")
    private Set<Plant> plants;

    public Disease() {}
    public Disease(String name, String treatment) {
        this.name = name;
        this.treatment = treatment;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
}