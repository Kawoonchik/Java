package com.example.plantcare.controller;

import com.example.plantcare.dto.PlantDto;
import com.example.plantcare.service.PlantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/plants")
public class PlantController {
    private final PlantService service;


    public PlantController(PlantService service) {
        this.service = service;
    }

    @GetMapping("/bad")
    public Page<PlantDto> getPlantsWithNPlusOne(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.getPlantsPage(PageRequest.of(page, size));
    }

    @GetMapping("/good")
    public Page<PlantDto> getPlantsOptimized(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.getPlantsPageOptimized(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public PlantDto getById(@PathVariable Long id) {
        return service.getPlantById(id);
    }

    @PostMapping
    public PlantDto create(@Valid @RequestBody PlantDto dto) {
        return service.createPlant(dto);
    }
}