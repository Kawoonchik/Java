package com.example.plantcare.controller;

import com.example.plantcare.dto.CareLogDto;
import com.example.plantcare.mapper.CareLogMapper;
import com.example.plantcare.model.CareLog;
import com.example.plantcare.service.CareLogService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/care-logs")
public class CareLogController {
    private final CareLogService service;
    private final CareLogMapper mapper;

    public CareLogController(CareLogService service, CareLogMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<CareLogDto> getAll() {
        return service.getAllCareLogs().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public CareLogDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getCareLogById(id));
    }

    @PostMapping
    public CareLogDto create(@Valid @RequestBody CareLogDto dto) {
        CareLog saved = service.createCareLog(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public CareLogDto replace(@PathVariable Long id, @Valid @RequestBody CareLogDto dto) {
        CareLog replaced = service.replaceCareLog(id, mapper.toEntity(dto));
        return mapper.toDto(replaced);
    }

    @PatchMapping("/{id}")
    public CareLogDto update(@PathVariable Long id, @RequestBody CareLogDto dto) {
        CareLog updated = service.updateCareLog(id, mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCareLog(id);
    }
}