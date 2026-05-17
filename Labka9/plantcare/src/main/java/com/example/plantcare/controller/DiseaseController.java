package com.example.plantcare.controller;

import com.example.plantcare.dto.DiseaseDto;
import com.example.plantcare.mapper.DiseaseMapper;
import com.example.plantcare.model.Disease;
import com.example.plantcare.service.DiseaseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diseases")
public class DiseaseController {
    private final DiseaseService service;
    private final DiseaseMapper mapper;

    public DiseaseController(DiseaseService service, DiseaseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DiseaseDto> getAll() {
        return service.getAllDiseases().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public DiseaseDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getDiseaseById(id));
    }

    @PostMapping
    public DiseaseDto create(@Valid @RequestBody DiseaseDto dto) {
        Disease saved = service.createDisease(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public DiseaseDto replace(@PathVariable Long id, @Valid @RequestBody DiseaseDto dto) {
        Disease replaced = service.replaceDisease(id, mapper.toEntity(dto));
        return mapper.toDto(replaced);
    }

    @PatchMapping("/{id}")
    public DiseaseDto update(@PathVariable Long id, @RequestBody DiseaseDto dto) {
        Disease updated = service.updateDisease(id, mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDisease(id);
    }
}