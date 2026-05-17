package com.example.plantcare.controller;

import com.example.plantcare.model.Disease;
import com.example.plantcare.service.DiseaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diseases")
public class DiseaseController {
    private final DiseaseService service;

    public DiseaseController(DiseaseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Disease> getAll() {
        return service.getAllDiseases();
    }

    @GetMapping("/{id}")
    public Disease getById(@PathVariable String id) {
        return service.getDiseaseById(id);
    }

    @PostMapping
    public Disease create(@RequestBody Disease disease) {
        return service.createDisease(disease);
    }

    @PutMapping("/{id}")
    public Disease replace(@PathVariable String id, @RequestBody Disease disease) {
        return service.replaceDisease(id, disease);
    }

    @PatchMapping("/{id}")
    public Disease update(@PathVariable String id, @RequestBody Disease disease) {
        return service.updateDisease(id, disease);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteDisease(id);
    }
}