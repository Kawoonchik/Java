package com.example.plantcare.controller;

import com.example.plantcare.model.User;
import com.example.plantcare.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable String id) {
        return service.getUserById(id);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping("/{id}")
    public User replace(@PathVariable String id, @RequestBody User user) {
        return service.replaceUser(id, user);
    }

    @PatchMapping("/{id}")
    public User update(@PathVariable String id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteUser(id);
    }
}