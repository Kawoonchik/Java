package com.example.plantcare.dto;

import jakarta.validation.constraints.NotBlank;

public class DiseaseDto {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String treatment;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
}