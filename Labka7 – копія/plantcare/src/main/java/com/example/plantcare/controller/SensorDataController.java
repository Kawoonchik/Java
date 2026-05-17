package com.example.plantcare.controller;

import com.example.plantcare.model.SensorData;
import com.example.plantcare.service.SensorDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor-data")
public class SensorDataController {
    private final SensorDataService service;

    public SensorDataController(SensorDataService service) {
        this.service = service;
    }

    @GetMapping
    public List<SensorData> getAll() {
        return service.getAllSensorData();
    }

    @GetMapping("/{id}")
    public SensorData getById(@PathVariable String id) {
        return service.getSensorDataById(id);
    }

    @PostMapping
    public SensorData create(@RequestBody SensorData sensorData) {
        return service.createSensorData(sensorData);
    }

    @PutMapping("/{id}")
    public SensorData replace(@PathVariable String id, @RequestBody SensorData sensorData) {
        return service.replaceSensorData(id, sensorData);
    }

    @PatchMapping("/{id}")
    public SensorData update(@PathVariable String id, @RequestBody SensorData sensorData) {
        return service.updateSensorData(id, sensorData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteSensorData(id);
    }
}