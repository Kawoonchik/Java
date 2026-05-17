package com.example.plantcare.service;

import com.example.plantcare.model.User;
import com.example.plantcare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public User replaceUser(Long id, User user) {
        user.setId(id);
        return repository.save(user);
    }

    public User updateUser(Long id, User userUpdates) {
        User existing = repository.findById(id).orElse(null);
        if (existing != null) {
            if (userUpdates.getUsername() != null) existing.setUsername(userUpdates.getUsername());
            if (userUpdates.getEmail() != null) existing.setEmail(userUpdates.getEmail());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}