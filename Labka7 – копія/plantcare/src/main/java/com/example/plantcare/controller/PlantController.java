package com.example.plantcare.controller;

import com.example.plantcare.model.Plant;
import com.example.plantcare.service.PlantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
public class PlantController {
    private final PlantService service;

    public PlantController(PlantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Plant> getAll() {
        return service.getAllPlants();
    }

    @GetMapping("/{id}")
    public Plant getById(@PathVariable String id) {
        return service.getPlantById(id);
    }

    @PostMapping
    public Plant create(@RequestBody Plant plant) {
        return service.createPlant(plant);
    }

    @PutMapping("/{id}")
    public Plant replace(@PathVariable String id, @RequestBody Plant plant) {
        return service.replacePlant(id, plant);
    }

    @PatchMapping("/{id}")
    public Plant update(@PathVariable String id, @RequestBody Plant plant) {
        return service.updatePlant(id, plant);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deletePlant(id);
    }
}