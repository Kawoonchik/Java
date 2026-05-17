package com.example.plantcare.controller;

import com.example.plantcare.model.CareLog;
import com.example.plantcare.service.CareLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/care-logs")
public class CareLogController {
    private final CareLogService service;

    public CareLogController(CareLogService service) {
        this.service = service;
    }

    @GetMapping
    public List<CareLog> getAll() {
        return service.getAllCareLogs();
    }

    @GetMapping("/{id}")
    public CareLog getById(@PathVariable String id) {
        return service.getCareLogById(id);
    }

    @PostMapping
    public CareLog create(@RequestBody CareLog careLog) {
        return service.createCareLog(careLog);
    }

    @PutMapping("/{id}")
    public CareLog replace(@PathVariable String id, @RequestBody CareLog careLog) {
        return service.replaceCareLog(id, careLog);
    }

    @PatchMapping("/{id}")
    public CareLog update(@PathVariable String id, @RequestBody CareLog careLog) {
        return service.updateCareLog(id, careLog);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteCareLog(id);
    }
}