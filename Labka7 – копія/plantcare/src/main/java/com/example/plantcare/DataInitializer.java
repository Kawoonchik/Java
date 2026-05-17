package com.example.plantcare;

import com.example.plantcare.model.*;
import com.example.plantcare.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PlantRepository plantRepository;
    private final UserRepository userRepository;
    private final DiseaseRepository diseaseRepository;

    public DataInitializer(PlantRepository plantRepository,
                           UserRepository userRepository,
                           DiseaseRepository diseaseRepository) {
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public void run(String... args) {
        // Початкові дані для рослин
        Plant p1 = new Plant("1", "Мій Спатіфілум", "Spathiphyllum");
        plantRepository.save(p1);

        // Початкові дані для користувачів
        User u1 = new User("1", "Admin_Root", "admin@plantcare.com");
        userRepository.save(u1);

        // Початкові дані для хвороб
        Disease d1 = new Disease("1", "Коренева гниль", "Зменшити полив, пересадити в сухий ґрунт");
        diseaseRepository.save(d1);

        System.out.println(">>> База даних (in-memory) успішно наповнена початковими даними!");
    }
}