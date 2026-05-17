package com.example.plantcare.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import java.util.List;

public class PlantDto {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String species;

    private Long userId;
    private SensorDataDto sensorData;
    private List<Long> careLogIds;
    private Set<Long> diseaseIds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public SensorDataDto getSensorData() { return sensorData; }
    public void setSensorData(SensorDataDto sensorData) { this.sensorData = sensorData; }
    public List<Long> getCareLogIds() { return careLogIds; }
    public void setCareLogIds(List<Long> careLogIds) { this.careLogIds = careLogIds; }
    public Set<Long> getDiseaseIds() { return diseaseIds; }
    public void setDiseaseIds(Set<Long> diseaseIds) { this.diseaseIds = diseaseIds; }
}