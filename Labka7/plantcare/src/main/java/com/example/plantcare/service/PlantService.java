package com.example.plantcare.service;

import com.example.plantcare.model.Plant;
import com.example.plantcare.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    private final PlantRepository repository;

    public PlantService(PlantRepository repository) {
        this.repository = repository;
    }

    public List<Plant> getAllPlants() {
        return repository.findAll();
    }

    public Plant getPlantById(Long id) {

        return repository.findById(id).orElse(null);
    }

    public Plant createPlant(Plant plant) {
        return repository.save(plant);
    }


    public Plant replacePlant(Long id, Plant plant) {
        plant.setId(id);
        return repository.save(plant);
    }

    public Plant updatePlant(Long id, Plant plantUpdates) {
        Optional<Plant> existingOptional = repository.findById(id);

        if (existingOptional.isPresent()) {
            Plant existing = existingOptional.get();
            if (plantUpdates.getName() != null) existing.setName(plantUpdates.getName());
            if (plantUpdates.getSpecies() != null) existing.setSpecies(plantUpdates.getSpecies());
            return repository.save(existing);
        }
        return null;
    }

    public void deletePlant(Long id) {
        repository.deleteById(id);
    }
}
