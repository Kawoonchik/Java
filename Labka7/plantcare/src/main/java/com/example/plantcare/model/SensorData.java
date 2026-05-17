package com.example.plantcare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plant_id")
    private Long plantId;

    @Column(name = "moisture_level")
    private int moistureLevel;

    @Column(name = "light_level")
    private int lightLevel;

    public SensorData() {}

    public SensorData(Long plantId, int moistureLevel, int lightLevel) {
        this.plantId = plantId;
        this.moistureLevel = moistureLevel;
        this.lightLevel = lightLevel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPlantId() { return plantId; }
    public void setPlantId(Long plantId) { this.plantId = plantId; }

    public int getMoistureLevel() { return moistureLevel; }
    public void setMoistureLevel(int moistureLevel) { this.moistureLevel = moistureLevel; }

    public int getLightLevel() { return lightLevel; }
    public void setLightLevel(int lightLevel) { this.lightLevel = lightLevel; }
}