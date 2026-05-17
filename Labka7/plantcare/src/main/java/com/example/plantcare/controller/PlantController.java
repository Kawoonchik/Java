//package com.example.plantcare.controller;
//
//import com.example.plantcare.model.Plant;
//import com.example.plantcare.service.PlantService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/plants")
//public class PlantController {
//    private final PlantService service;
//
//    public PlantController(PlantService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<Plant> getAll() {
//        return service.getAllPlants();
//    }
//
//    @GetMapping("/{id}")
//    public Plant getById(@PathVariable Long id) {
//        return service.getPlantById(id);
//    }
//
//    @PostMapping
//    public Plant create(@RequestBody Plant plant) {
//        return service.createPlant(plant);
//    }
//
//    @PutMapping("/{id}")
//    public Plant replace(@PathVariable Long id, @RequestBody Plant plant) {
//        return service.replacePlant(id, plant);
//    }
//
//    @PatchMapping("/{id}")
//    public Plant update(@PathVariable Long id, @RequestBody Plant plant) {
//        return service.updatePlant(id, plant);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        service.deletePlant(id);
//    }
//}


package com.example.plantcare.controller;

import com.example.plantcare.dto.PlantDto;
import com.example.plantcare.mapper.PlantMapper;
import com.example.plantcare.model.Plant;
import com.example.plantcare.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    private final PlantService service;
    private final PlantMapper mapper;

    public PlantController(PlantService service, PlantMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<PlantDto> getAll() {
        return service.getAllPlants().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantDto> getById(@PathVariable Long id) {
        Plant plant = service.getPlantById(id);
        if (plant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDto(plant));
    }

    @PostMapping
    public ResponseEntity<PlantDto> create(@Valid @RequestBody PlantDto plantDto) {
        //DTO з запиту в Entity
        Plant plant = mapper.toEntity(plantDto);
        //Зберігання через сервіс
        Plant savedPlant = service.createPlant(plant);
        //назад DTO
        return ResponseEntity.ok(mapper.toDto(savedPlant));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PlantDto> update(@PathVariable Long id, @Valid @RequestBody PlantDto plantDto) {
        Plant plantToUpdate = mapper.toEntity(plantDto);
        Plant updatedPlant = service.replacePlant(id, plantToUpdate);

        if (updatedPlant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDto(updatedPlant));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletePlant(id);
        return ResponseEntity.noContent().build(); // Повертає статус 204 (успішно, без контенту)
    }
}