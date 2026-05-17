package com.example.plantcare.model;

public class SensorData {
    private String id;
    private String plantId;
    private int moistureLevel;
    private int lightLevel;

    public SensorData() {}

    public SensorData(String id, String plantId, int moistureLevel, int lightLevel) {
        this.id = id;
        this.plantId = plantId;
        this.moistureLevel = moistureLevel;
        this.lightLevel = lightLevel;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPlantId() { return plantId; }
    public void setPlantId(String plantId) { this.plantId = plantId; }
    public int getMoistureLevel() { return moistureLevel; }
    public void setMoistureLevel(int moistureLevel) { this.moistureLevel = moistureLevel; }
    public int getLightLevel() { return lightLevel; }
    public void setLightLevel(int lightLevel) { this.lightLevel = lightLevel; }
}