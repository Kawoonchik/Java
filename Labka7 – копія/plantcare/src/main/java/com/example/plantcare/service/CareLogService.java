package com.example.plantcare.service;

import com.example.plantcare.model.CareLog;
import com.example.plantcare.repository.CareLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CareLogService {
    private final CareLogRepository repository;

    public CareLogService(CareLogRepository repository) {
        this.repository = repository;
    }

    public List<CareLog> getAllCareLogs() {
        return repository.findAll();
    }

    public CareLog getCareLogById(String id) {
        return repository.findById(id);
    }

    public CareLog createCareLog(CareLog careLog) {
        careLog.setId(UUID.randomUUID().toString());
        return repository.save(careLog);
    }

    public CareLog replaceCareLog(String id, CareLog careLog) {
        careLog.setId(id);
        return repository.save(careLog);
    }

    public CareLog updateCareLog(String id, CareLog careLogUpdates) {
        CareLog existing = repository.findById(id);
        if (existing != null) {
            if (careLogUpdates.getPlantId() != null) existing.setPlantId(careLogUpdates.getPlantId());
            if (careLogUpdates.getActionType() != null) existing.setActionType(careLogUpdates.getActionType());
            if (careLogUpdates.getDate() != null) existing.setDate(careLogUpdates.getDate());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteCareLog(String id) {
        repository.deleteById(id);
    }
}