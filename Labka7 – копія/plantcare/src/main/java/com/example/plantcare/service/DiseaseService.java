package com.example.plantcare.service;

import com.example.plantcare.model.Disease;
import com.example.plantcare.repository.DiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DiseaseService {
    private final DiseaseRepository repository;

    public DiseaseService(DiseaseRepository repository) {
        this.repository = repository;
    }

    public List<Disease> getAllDiseases() {
        return repository.findAll();
    }

    public Disease getDiseaseById(String id) {
        return repository.findById(id);
    }

    public Disease createDisease(Disease disease) {
        disease.setId(UUID.randomUUID().toString());
        return repository.save(disease);
    }

    public Disease replaceDisease(String id, Disease disease) {
        disease.setId(id);
        return repository.save(disease);
    }

    public Disease updateDisease(String id, Disease diseaseUpdates) {
        Disease existing = repository.findById(id);
        if (existing != null) {
            if (diseaseUpdates.getName() != null) existing.setName(diseaseUpdates.getName());
            if (diseaseUpdates.getTreatment() != null) existing.setTreatment(diseaseUpdates.getTreatment());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteDisease(String id) {
        repository.deleteById(id);
    }
}