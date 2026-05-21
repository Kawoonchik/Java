package com.example.plantcare.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "plants")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "plant", cascade = CascadeType.ALL)
    private SensorData sensorData;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    private List<CareLog> careLogs;

    @ManyToMany
    @JoinTable(
            name = "plant_diseases",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private Set<Disease> diseases;

    public Plant() {}
    public Plant(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public SensorData getSensorData() { return sensorData; }
    public void setSensorData(SensorData sensorData) { this.sensorData = sensorData; }

    public List<CareLog> getCareLogs() { return careLogs; }
    public void setCareLogs(List<CareLog> careLogs) { this.careLogs = careLogs; }

    public Set<Disease> getDiseases() { return diseases; }
    public void setDiseases(Set<Disease> diseases) { this.diseases = diseases; }
}