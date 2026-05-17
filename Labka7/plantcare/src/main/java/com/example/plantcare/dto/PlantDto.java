package com.example.plantcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlantDto {

    private Long id;

    @NotBlank(message = "Ім'я рослини не може бути порожнім")
    @Size(min = 2, max = 50, message = "Ім'я має містити від 2 до 50 символів")
    private String name;

    @NotBlank(message = "Вид рослини є обов'язковим")
    private String species;

    public PlantDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
}