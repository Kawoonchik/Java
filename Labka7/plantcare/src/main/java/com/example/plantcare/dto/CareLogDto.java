package com.example.plantcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CareLogDto {
    private Long id;

    @NotNull
    private Long plantId;

    @NotBlank
    private String actionType;

    @NotBlank
    private String date;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPlantId() { return plantId; }
    public void setPlantId(Long plantId) { this.plantId = plantId; }

    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}