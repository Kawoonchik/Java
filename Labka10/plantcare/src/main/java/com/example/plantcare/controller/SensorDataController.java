package com.example.plantcare.controller;

import com.example.plantcare.dto.SensorDataDto;
import com.example.plantcare.mapper.SensorDataMapper;
import com.example.plantcare.model.SensorData;
import com.example.plantcare.service.SensorDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor-data")
@Tag(name = "Sensor Data API", description = "Операції для управління показниками датчиків (вологість, світло)")
public class SensorDataController {
    private final SensorDataService service;
    private final SensorDataMapper mapper;

    public SensorDataController(SensorDataService service, SensorDataMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Отримати всі показники датчиків", description = "Повертає список поточних даних з датчиків для всіх рослин.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані успішно отримано",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SensorDataDto.class)))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @GetMapping
    public List<SensorDataDto> getAll() {
        return service.getAllSensorData().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Operation(summary = "Отримати показники датчика за ID", description = "Шукає та повертає конкретні метрики датчиків за ідентифікатором запису.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Показники знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDataDto.class))),
            @ApiResponse(responseCode = "404", description = "Запис показників не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @GetMapping("/{id}")
    public SensorDataDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getSensorDataById(id));
    }

    @Operation(summary = "Додати нові метрики датчика", description = "Зберігає нові показники датчиків (рівень вологи та світла) для вказаної рослини.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Показники датчиків успішно додано",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDataDto.class))),
            @ApiResponse(responseCode = "400", description = "Невірний формат даних (відсутній обов'язковий plantId)",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"plantId\": \"must not be null\"}"))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PostMapping
    public SensorDataDto create(@Valid @RequestBody SensorDataDto dto) {
        SensorData saved = service.createSensorData(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Operation(summary = "Замінити показники датчика (PUT)", description = "Повністю оновлює метрики датчика за переданим ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Показники успішно замінено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDataDto.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації полів", content = @Content),
            @ApiResponse(responseCode = "404", description = "Запис датчика не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PutMapping("/{id}")
    public SensorDataDto replace(@PathVariable Long id, @Valid @RequestBody SensorDataDto dto) {
        SensorData replaced = service.replaceSensorData(id, mapper.toEntity(dto));
        return mapper.toDto(replaced);
    }

    @Operation(summary = "Оновити показники датчика частково (PATCH)", description = "Дозволяє оновити окрему метрику (наприклад, тільки рівень світла lightLevel).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Метрики успішно модифіковано",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDataDto.class))),
            @ApiResponse(responseCode = "404", description = "Запис показників не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PatchMapping("/{id}")
    public SensorDataDto update(@PathVariable Long id, @RequestBody SensorDataDto dto) {
        SensorData updated = service.updateSensorData(id, mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @Operation(summary = "Видалити запис показників датчика", description = "Видаляє метрики датчика з бази даних за вказаним ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис успішно видалено", content = @Content),
            @ApiResponse(responseCode = "404", description = "Запис для видалення не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteSensorData(id);
    }
}