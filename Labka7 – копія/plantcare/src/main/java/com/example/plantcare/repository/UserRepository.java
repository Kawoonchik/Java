package com.example.plantcare.repository;

import com.example.plantcare.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<String, User> storage = new ConcurrentHashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(storage.values());
    }

    public User findById(String id) {
        return storage.get(id);
    }

    public User save(User user) {
        storage.put(user.getId(), user);
        return user;
    }

    public void deleteById(String id) {
        storage.remove(id);
    }
}