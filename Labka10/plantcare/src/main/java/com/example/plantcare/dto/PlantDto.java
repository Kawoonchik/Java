package com.example.plantcare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import java.util.List;

@Schema(description = "Data Transfer Object для сутності Рослина")
public class PlantDto {

    @Schema(description = "Унікальний ідентифікатор рослини", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Назва рослини", example = "Мій улюблений спатифілум")
    private String name;

    @NotBlank
    @Schema(description = "Вид рослини", example = "Spathiphyllum")
    private String species;

    @Schema(description = "Ідентифікатор користувача-власника", example = "10")
    private Long userId;

    @Schema(description = "Дані з датчиків для цієї рослини")
    private SensorDataDto sensorData;

    @Schema(description = "Список ідентифікаторів записів догляду")
    private List<Long> careLogIds;

    @Schema(description = "Список ідентифікаторів хвороб")
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