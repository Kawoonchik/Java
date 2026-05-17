package com.example.plantcare.repository;

import com.example.plantcare.model.SensorData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SensorDataRepository {
    private final Map<String, SensorData> storage = new ConcurrentHashMap<>();

    public List<SensorData> findAll() {
        return new ArrayList<>(storage.values());
    }

    public SensorData findById(String id) {
        return storage.get(id);
    }

    public SensorData save(SensorData sensorData) {
        storage.put(sensorData.getId(), sensorData);
        return sensorData;
    }

    public void deleteById(String id) {
        storage.remove(id);
    }
}