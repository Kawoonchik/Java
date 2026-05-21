package com.example.plantcare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO для даних з датчиків")
public class SensorDataDto {

    @Schema(description = "Унікальний ідентифікатор запису датчика", example = "1")
    private Long id;

    @NotNull
    @Schema(description = "Ідентифікатор рослини", example = "5")
    private Long plantId;

    @Schema(description = "Рівень вологості ґрунту (у відсотках)", example = "65")
    private int moistureLevel;

    @Schema(description = "Рівень освітленості (у люксах)", example = "300")
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