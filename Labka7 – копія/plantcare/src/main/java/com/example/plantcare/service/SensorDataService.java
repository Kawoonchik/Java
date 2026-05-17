package com.example.plantcare.service;

import com.example.plantcare.model.SensorData;
import com.example.plantcare.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SensorDataService {
    private final SensorDataRepository repository;

    public SensorDataService(SensorDataRepository repository) {
        this.repository = repository;
    }

    public List<SensorData> getAllSensorData() {
        return repository.findAll();
    }

    public SensorData getSensorDataById(String id) {
        return repository.findById(id);
    }

    public SensorData createSensorData(SensorData sensorData) {
        sensorData.setId(UUID.randomUUID().toString());
        return repository.save(sensorData);
    }

    public SensorData replaceSensorData(String id, SensorData sensorData) {
        sensorData.setId(id);
        return repository.save(sensorData);
    }

    public SensorData updateSensorData(String id, SensorData sensorDataUpdates) {
        SensorData existing = repository.findById(id);
        if (existing != null) {
            if (sensorDataUpdates.getPlantId() != null) existing.setPlantId(sensorDataUpdates.getPlantId());
            if (sensorDataUpdates.getMoistureLevel() != 0) existing.setMoistureLevel(sensorDataUpdates.getMoistureLevel());
            if (sensorDataUpdates.getLightLevel() != 0) existing.setLightLevel(sensorDataUpdates.getLightLevel());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteSensorData(String id) {
        repository.deleteById(id);
    }
}