package com.example.plantcare.service;

import com.example.plantcare.dto.PlantDto;
import com.example.plantcare.mapper.PlantMapper;
import com.example.plantcare.model.Plant;
import com.example.plantcare.repository.PlantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

@Service
public class PlantService {
    private final PlantRepository repository;
    private final PlantMapper mapper;

    public PlantService(PlantRepository repository, PlantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //Длячитання readOnly = true бо це зекономить ресурси
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<PlantDto> getPlantsPage(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<PlantDto> getPlantsPageOptimized(Pageable pageable) {
        return repository.findAllWithFetch(pageable).map(mapper::toDto);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PlantDto getPlantById(Long id) {
        Plant plant = repository.findById(id).orElse(null);
        return plant != null ? mapper.toDto(plant) : null;
    }

    //  REQUIRED бо нада
    @Transactional(propagation = Propagation.REQUIRED)
    public PlantDto createPlant(PlantDto plantDto) {
        Plant saved = repository.save(mapper.toEntity(plantDto));
        return mapper.toDto(saved);
    }
}