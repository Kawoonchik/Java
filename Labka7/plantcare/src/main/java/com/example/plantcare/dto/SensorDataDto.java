package com.example.plantcare.dto;

import jakarta.validation.constraints.NotNull;

public class SensorDataDto {
    private Long id;

    @NotNull
    private Long plantId;

    private int moistureLevel;
    private int lightLevel;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPlantId() { return plantId; }
    public void setPlantId(Long plantId) { this.plantId = plantId; }

    public int getMoistureLevel() { return moistureLevel; }
    public void setMoistureLevel(int moistureLevel) { this.moistureLevel = moistureLevel; }

    public int getLightLevel() { return lightLevel; }
    public void setLightLevel(int lightLevel) { this.lightLevel = lightLevel; }
}