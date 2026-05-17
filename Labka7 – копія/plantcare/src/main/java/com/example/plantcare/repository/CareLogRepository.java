package com.example.plantcare.repository;

import com.example.plantcare.model.CareLog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CareLogRepository {
    private final Map<String, CareLog> storage = new ConcurrentHashMap<>();

    public List<CareLog> findAll() {
        return new ArrayList<>(storage.values());
    }

    public CareLog findById(String id) {
        return storage.get(id);
    }

    public CareLog save(CareLog careLog) {
        storage.put(careLog.getId(), careLog);
        return careLog;
    }

    public void deleteById(String id) {
        storage.remove(id);
    }
}