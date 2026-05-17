package com.example.plantcare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor_data")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;

    private int moistureLevel;
    private int lightLevel;

    public SensorData() {}
    public SensorData(Plant plant, int moistureLevel, int lightLevel) {
        this.plant = plant;
        this.moistureLevel = moistureLevel;
        this.lightLevel = lightLevel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Plant getPlant() { return plant; }
    public void setPlant(Plant plant) { this.plant = plant; }
    public int getMoistureLevel() { return moistureLevel; }
    public void setMoistureLevel(int moistureLevel) { this.moistureLevel = moistureLevel; }
    public int getLightLevel() { return lightLevel; }
    public void setLightLevel(int lightLevel) { this.lightLevel = lightLevel; }
}