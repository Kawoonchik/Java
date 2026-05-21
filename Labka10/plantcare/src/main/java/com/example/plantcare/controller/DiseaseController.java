package com.example.plantcare.controller;

import com.example.plantcare.dto.DiseaseDto;
import com.example.plantcare.mapper.DiseaseMapper;
import com.example.plantcare.model.Disease;
import com.example.plantcare.service.DiseaseService;
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
@RequestMapping("/api/diseases")
@Tag(name = "Diseases API", description = "Операції для управління хворобами та методами лікування рослин")
public class DiseaseController {
    private final DiseaseService service;
    private final DiseaseMapper mapper;

    public DiseaseController(DiseaseService service, DiseaseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Отримати всі хвороби", description = "Повертає перелік усіх зафіксованих у довіднику хвороб рослин.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список хвороб успішно отримано",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DiseaseDto.class)))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @GetMapping
    public List<DiseaseDto> getAll() {
        return service.getAllDiseases().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Operation(summary = "Отримати хворобу за ID", description = "Повертає опис хвороби та методи її лікування за ідентифікатором.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Хворобу успішно знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DiseaseDto.class))),
            @ApiResponse(responseCode = "404", description = "Хворобу з таким ID не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @GetMapping("/{id}")
    public DiseaseDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getDiseaseById(id));
    }

    @Operation(summary = "Додати нову хворобу", description = "Створює новий запис хвороби з відповідними рекомендаціями щодо лікування.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Хворобу успішно додано",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DiseaseDto.class))),
            @ApiResponse(responseCode = "400", description = "Некоректні вхідні дані (наприклад, пуста назва хвороби)",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"name\": \"must not be blank\"}"))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PostMapping
    public DiseaseDto create(@Valid @RequestBody DiseaseDto dto) {
        Disease saved = service.createDisease(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Operation(summary = "Замінити дані про хворобу (PUT)", description = "Повністю оновлює інформацію про хворобу за її унікальним ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані успішно замінено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DiseaseDto.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації даних", content = @Content),
            @ApiResponse(responseCode = "404", description = "Запис хвороби не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PutMapping("/{id}")
    public DiseaseDto replace(@PathVariable Long id, @Valid @RequestBody DiseaseDto dto) {
        Disease replaced = service.replaceDisease(id, mapper.toEntity(dto));
        return mapper.toDto(replaced);
    }

    @Operation(summary = "Модифікувати дані про хворобу (PATCH)", description = "Частково змінює поля запису хвороби (наприклад, тільки опис лікування).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис успішно модифіковано",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DiseaseDto.class))),
            @ApiResponse(responseCode = "404", description = "Запис хвороби не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PatchMapping("/{id}")
    public DiseaseDto update(@PathVariable Long id, @RequestBody DiseaseDto dto) {
        Disease updated = service.updateDisease(id, mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @Operation(summary = "Видалити запис хвороби", description = "Видаляє хворобу з довідника бази даних за її ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис хвороби успішно видалено", content = @Content),
            @ApiResponse(responseCode = "404", description = "Запис хвороби не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDisease(id);
    }
}