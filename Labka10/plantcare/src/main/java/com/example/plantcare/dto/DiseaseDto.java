package com.example.plantcare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO для хвороби рослини")
public class DiseaseDto {

    @Schema(description = "Унікальний ідентифікатор хвороби", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Назва хвороби", example = "Борошниста роса")
    private String name;

    @NotBlank
    @Schema(description = "Рекомендоване лікування", example = "Обробити фунгіцидом")
    private String treatment;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
}