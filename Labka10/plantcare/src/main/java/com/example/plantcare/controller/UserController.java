package com.example.plantcare.controller;

import com.example.plantcare.dto.UserDto;
import com.example.plantcare.mapper.UserMapper;
import com.example.plantcare.model.User;
import com.example.plantcare.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return service.getAllUsers().stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return mapper.toDto(service.getUserById(id));
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto dto) {
        User saved = service.createUser(mapper.toEntity(dto));
        return mapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public UserDto replace(@PathVariable Long id, @Valid @RequestBody UserDto dto) {
        User replaced = service.replaceUser(id, mapper.toEntity(dto));
        return mapper.toDto(replaced);
    }

    @PatchMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto) {
        User updated = service.updateUser(id, mapper.toEntity(dto));
        return mapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }
}