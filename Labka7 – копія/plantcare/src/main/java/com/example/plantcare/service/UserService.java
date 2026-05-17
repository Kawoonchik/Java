package com.example.plantcare.service;

import com.example.plantcare.model.User;
import com.example.plantcare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(String id) {
        return repository.findById(id);
    }

    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return repository.save(user);
    }

    public User replaceUser(String id, User user) {
        user.setId(id);
        return repository.save(user);
    }

    public User updateUser(String id, User userUpdates) {
        User existing = repository.findById(id);
        if (existing != null) {
            if (userUpdates.getUsername() != null) existing.setUsername(userUpdates.getUsername());
            if (userUpdates.getEmail() != null) existing.setEmail(userUpdates.getEmail());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteUser(String id) {
        repository.deleteById(id);
    }
}