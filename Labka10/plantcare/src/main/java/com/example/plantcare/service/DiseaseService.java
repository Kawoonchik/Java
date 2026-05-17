package com.example.plantcare.service;

import com.example.plantcare.model.Disease;
import com.example.plantcare.repository.DiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseService {
    private final DiseaseRepository repository;

    public DiseaseService(DiseaseRepository repository) {
        this.repository = repository;
    }

    public List<Disease> getAllDiseases() {
        return repository.findAll();
    }

    public Disease getDiseaseById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Disease createDisease(Disease disease) {
        return repository.save(disease);
    }

    public Disease replaceDisease(Long id, Disease disease) {
        disease.setId(id);
        return repository.save(disease);
    }

    public Disease updateDisease(Long id, Disease diseaseUpdates) {
        Disease existing = repository.findById(id).orElse(null);
        if (existing != null) {
            if (diseaseUpdates.getName() != null) existing.setName(diseaseUpdates.getName());
            if (diseaseUpdates.getTreatment() != null) existing.setTreatment(diseaseUpdates.getTreatment());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteDisease(Long id) {
        repository.deleteById(id);
    }
}