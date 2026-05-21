package com.example.plantcare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO для запису догляду за рослиною")
public class CareLogDto {

    @Schema(description = "Унікальний ідентифікатор запису", example = "100")
    private Long id;

    @NotNull
    @Schema(description = "Ідентифікатор рослини, до якої відноситься запис", example = "5")
    private Long plantId;

    @NotBlank
    @Schema(description = "Тип виконаної дії", example = "Полив")
    private String actionType;

    @NotBlank
    @Schema(description = "Дата виконання дії", example = "2026-05-15")
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