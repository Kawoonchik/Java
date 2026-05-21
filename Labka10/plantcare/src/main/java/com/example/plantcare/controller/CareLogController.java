package com.example.plantcare.controller;

import com.example.plantcare.dto.CareLogDto;
import com.example.plantcare.mapper.CareLogMapper;
import com.example.plantcare.model.CareLog;
import com.example.plantcare.service.CareLogService;
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
@RequestMapping("/api/care-logs")
@Tag(name = "Care Logs API", description = "Операції для управління записами догляду за рослинами")
public class CareLogController {
    private final CareLogService service;
    private final CareLogMapper mapper;

    public CareLogController(CareLogService service, CareLogMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Отримати всі записи догляду", description = "Повертає повний список усіх подій догляду (полив, підживлення тощо) у системі.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список записів успішно отримано",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CareLogDto.class)))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @GetMapping
    public List<CareLogDto> getAll() {
        return service.getAllCareLogs().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Operation(summary = "Отримати запис догляду за ID", description = "Шукає та повертає конкретну подію догляду за її унікальним ідентифікатором.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис успішно знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CareLogDto.class))),
            @ApiResponse(responseCode = "404", description = "Запис із таким ID не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @GetMapping("/{id}")
    public CareLogDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getCareLogById(id));
    }

    @Operation(summary = "Створити новий запис догляду", description = "Додає нову подію догляду для конкретної рослини з валідацією полів запиту.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис успішно створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CareLogDto.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних",
                    content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"actionType\": \"must not be blank\"}"))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PostMapping
    public CareLogDto create(@Valid @RequestBody CareLogDto dto) {
        CareLog saved = service.createCareLog(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Operation(summary = "Повне оновлення запису догляду (PUT)", description = "Замінює всі поля існуючого запису догляду за вказаним ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис успішно замінено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CareLogDto.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації оновлених полів", content = @Content),
            @ApiResponse(responseCode = "404", description = "Запис для заміни не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PutMapping("/{id}")
    public CareLogDto replace(@PathVariable Long id, @Valid @RequestBody CareLogDto dto) {
        CareLog replaced = service.replaceCareLog(id, mapper.toEntity(dto));
        return mapper.toDto(replaced);
    }

    @Operation(summary = "Часткове оновлення запису догляду (PATCH)", description = "Дозволяє змінити окремі поля запису догляду без повної заміни об'єкта.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис успішно оновлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CareLogDto.class))),
            @ApiResponse(responseCode = "404", description = "Запис для оновлення не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @PatchMapping("/{id}")
    public CareLogDto update(@PathVariable Long id, @RequestBody CareLogDto dto) {
        CareLog updated = service.updateCareLog(id, mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @Operation(summary = "Видалити запис догляду", description = "Видаляє інформацію про подію догляду з бази даних за її ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запис успішно видалено", content = @Content),
            @ApiResponse(responseCode = "404", description = "Запис не знайдено в системі", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteCareLog(id);
    }
}