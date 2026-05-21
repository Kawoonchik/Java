package com.example.plantcare.controller;

import com.example.plantcare.dto.PlantDto;
import com.example.plantcare.service.PlantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/plants")
@Tag(name = "Plants API", description = "Операції для управління рослинами")
public class PlantController {
    private final PlantService service;

    public PlantController(PlantService service) {
        this.service = service;
    }

    @Operation(summary = "Отримати рослину за ID", description = "Повертає деталі рослини на основі її унікального ідентифікатора.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рослину успішно знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlantDto.class))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", // Пункт 6: Документування помилок
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"error\": \"Сталася внутрішня помилка сервера: повідомлення\"}")))
    })
    @GetMapping("/{id}")
    public PlantDto getById(@PathVariable Long id) {
        return service.getPlantById(id);
    }

    @Operation(summary = "Створити нову рослину", description = "Додає нову рослину до бази даних із валідацією вхідних даних.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рослину успішно створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlantDto.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних", // Пункт 6: Помилки валідації
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"name\": \"must not be blank\"}"))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public PlantDto create(@Valid @RequestBody PlantDto dto) {
        return service.createPlant(dto);
    }

    @Operation(summary = "Отримати всі рослини (N+1 проблема)", description = "Повертає сторінку з рослинами, демонструючи проблему N+1.")
    @GetMapping("/bad")
    public Page<PlantDto> getPlantsWithNPlusOne(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.getPlantsPage(PageRequest.of(page, size));
    }

    @Operation(summary = "Отримати всі рослини (Оптимізовано)", description = "Повертає сторінку з рослинами з використанням оптимізованих запитів бази даних.")
    @GetMapping("/good")
    public Page<PlantDto> getPlantsOptimized(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.getPlantsPageOptimized(PageRequest.of(page, size));
    }
}