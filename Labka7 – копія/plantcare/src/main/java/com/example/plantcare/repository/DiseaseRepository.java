package com.example.plantcare.repository;

import com.example.plantcare.model.Disease;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DiseaseRepository {
    private final Map<String, Disease> storage = new ConcurrentHashMap<>();

    public List<Disease> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Disease findById(String id) {
        return storage.get(id);
    }

    public Disease save(Disease disease) {
        storage.put(disease.getId(), disease);
        return disease;
    }

    public void deleteById(String id) {
        storage.remove(id);
    }
}