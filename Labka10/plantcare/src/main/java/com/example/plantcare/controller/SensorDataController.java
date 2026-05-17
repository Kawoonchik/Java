package com.example.plantcare.controller;

import com.example.plantcare.dto.SensorDataDto;
import com.example.plantcare.mapper.SensorDataMapper;
import com.example.plantcare.model.SensorData;
import com.example.plantcare.service.SensorDataService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor-data")
public class SensorDataController {
    private final SensorDataService service;
    private final SensorDataMapper mapper;

    public SensorDataController(SensorDataService service, SensorDataMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<SensorDataDto> getAll() {
        return service.getAllSensorData().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public SensorDataDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getSensorDataById(id));
    }

    @PostMapping
    public SensorDataDto create(@Valid @RequestBody SensorDataDto dto) {
        SensorData saved = service.createSensorData(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public SensorDataDto replace(@PathVariable Long id, @Valid @RequestBody SensorDataDto dto) {
        SensorData replaced = service.replaceSensorData(id, mapper.toEntity(dto));
        return mapper.toDto(replaced);
    }

    @PatchMapping("/{id}")
    public SensorDataDto update(@PathVariable Long id, @RequestBody SensorDataDto dto) {
        SensorData updated = service.updateSensorData(id, mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteSensorData(id);
    }
}