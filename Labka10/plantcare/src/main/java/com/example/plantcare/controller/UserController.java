package com.example.plantcare.controller;

import com.example.plantcare.dto.UserDto;
import com.example.plantcare.mapper.UserMapper;
import com.example.plantcare.model.User;
import com.example.plantcare.service.UserService;
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
@RequestMapping("/api/users")
@Tag(name = "Users API", description = "Операції для управління користувачами")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Отримати всіх користувачів", description = "Повертає список усіх користувачів, зареєстрованих у системі.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список успішно отримано",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @GetMapping
    public List<UserDto> getAll() {
        return service.getAllUsers().stream().map(mapper::toDto).toList();
    }

    @Operation(summary = "Отримати користувача за ID", description = "Шукає та повертає деталі користувача за його ідентифікатором.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Користувача знайдено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "Користувача не знайдено", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getUserById(id));
    }

    @Operation(summary = "Створити користувача", description = "Додає нового користувача з валідацією полів.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Користувача успішно створено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації (наприклад, невірний email)", content = @Content)
    })
    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto dto) {
        User saved = service.createUser(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @Operation(summary = "Замінити користувача (PUT)", description = "Повністю оновлює дані користувача за переданим ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Користувача успішно оновлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації", content = @Content),
            @ApiResponse(responseCode = "404", description = "Користувача не знайдено", content = @Content)
    })
    @PutMapping("/{id}")
    public UserDto replace(@PathVariable Long id, @Valid @RequestBody UserDto dto) {
        User replaced = service.replaceUser(id, mapper.toEntity(dto));
        return mapper.toDto(replaced);
    }

    @Operation(summary = "Оновити користувача (PATCH)", description = "Частково оновлює дані користувача (без повної валідації).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Користувача успішно оновлено",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "Користувача не знайдено", content = @Content)
    })
    @PatchMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto) {
        User updated = service.updateUser(id, mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @Operation(summary = "Видалити користувача", description = "Видаляє користувача з бази даних за його ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Користувача успішно видалено", content = @Content),
            @ApiResponse(responseCode = "404", description = "Користувача не знайдено", content = @Content)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }
}