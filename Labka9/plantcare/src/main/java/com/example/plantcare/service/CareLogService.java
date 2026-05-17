package com.example.plantcare.service;

import com.example.plantcare.model.CareLog;
import com.example.plantcare.repository.CareLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareLogService {
    private final CareLogRepository repository;

    public CareLogService(CareLogRepository repository) {
        this.repository = repository;
    }

    public List<CareLog> getAllCareLogs() {
        return repository.findAll();
    }

    public CareLog getCareLogById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CareLog createCareLog(CareLog careLog) {
        return repository.save(careLog);
    }

    public CareLog replaceCareLog(Long id, CareLog careLog) {
        careLog.setId(id);
        return repository.save(careLog);
    }

    public CareLog updateCareLog(Long id, CareLog careLogUpdates) {
        CareLog existing = repository.findById(id).orElse(null);
        if (existing != null) {
            if (careLogUpdates.getPlant() != null) existing.setPlant(careLogUpdates.getPlant());
            if (careLogUpdates.getActionType() != null) existing.setActionType(careLogUpdates.getActionType());
            if (careLogUpdates.getDate() != null) existing.setDate(careLogUpdates.getDate());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteCareLog(Long id) {
        repository.deleteById(id);
    }
}