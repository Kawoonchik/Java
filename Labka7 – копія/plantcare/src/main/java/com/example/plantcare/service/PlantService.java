package com.example.plantcare.service;

import com.example.plantcare.model.Plant;
import com.example.plantcare.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlantService {
    private final PlantRepository repository;

    public PlantService(PlantRepository repository) {
        this.repository = repository;
    }

    public List<Plant> getAllPlants() {
        return repository.findAll();
    }

    public Plant getPlantById(String id) {
        return repository.findById(id);
    }

    public Plant createPlant(Plant plant) {
        plant.setId(UUID.randomUUID().toString());
        return repository.save(plant);
    }

    public Plant replacePlant(String id, Plant plant) {
        plant.setId(id);
        return repository.save(plant);
    }

    public Plant updatePlant(String id, Plant plantUpdates) {
        Plant existing = repository.findById(id);
        if (existing != null) {
            if (plantUpdates.getName() != null) existing.setName(plantUpdates.getName());
            if (plantUpdates.getSpecies() != null) existing.setSpecies(plantUpdates.getSpecies());
            return repository.save(existing);
        }
        return null;
    }

    public void deletePlant(String id) {
        repository.deleteById(id);
    }
}