package com.example.plantcare.service;

import com.example.plantcare.model.SensorData;
import com.example.plantcare.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDataService {
    private final SensorDataRepository repository;

    public SensorDataService(SensorDataRepository repository) {
        this.repository = repository;
    }

    public List<SensorData> getAllSensorData() {
        return repository.findAll();
    }

    public SensorData getSensorDataById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public SensorData createSensorData(SensorData sensorData) {
        return repository.save(sensorData);
    }

    public SensorData replaceSensorData(Long id, SensorData sensorData) {
        sensorData.setId(id);
        return repository.save(sensorData);
    }

    public SensorData updateSensorData(Long id, SensorData sensorDataUpdates) {
        SensorData existing = repository.findById(id).orElse(null);
        if (existing != null) {
            if (sensorDataUpdates.getPlantId() != null) existing.setPlantId(sensorDataUpdates.getPlantId());
            if (sensorDataUpdates.getMoistureLevel() != 0) existing.setMoistureLevel(sensorDataUpdates.getMoistureLevel());
            if (sensorDataUpdates.getLightLevel() != 0) existing.setLightLevel(sensorDataUpdates.getLightLevel());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteSensorData(Long id) {
        repository.deleteById(id);
    }
}