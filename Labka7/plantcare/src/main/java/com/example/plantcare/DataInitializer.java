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
    private final CareLogRepository careLogRepository;
    private final SensorDataRepository sensorDataRepository;

    public DataInitializer(PlantRepository plantRepository,
                           UserRepository userRepository,
                           DiseaseRepository diseaseRepository,
                           CareLogRepository careLogRepository,
                           SensorDataRepository sensorDataRepository) {
        this.plantRepository = plantRepository;
        this.userRepository = userRepository;
        this.diseaseRepository = diseaseRepository;
        this.careLogRepository = careLogRepository;
        this.sensorDataRepository = sensorDataRepository;
    }

    @Override
    public void run(String... args) {
        Plant p1 = new Plant("Мій Спатіфілум", "Spathiphyllum");
        plantRepository.save(p1);

        User u1 = new User("Admin_Root", "admin@plantcare.com");
        userRepository.save(u1);

        Disease d1 = new Disease("Коренева гниль", "Зменшити полив, пересадити в сухий ґрунт");
        diseaseRepository.save(d1);

        CareLog c1 = new CareLog(p1.getId(), "Полив", "2026-05-05");
        careLogRepository.save(c1);

        SensorData s1 = new SensorData(p1.getId(), 45, 80);
        sensorDataRepository.save(s1);

        System.out.println(">>> База даних MySQL успішно наповнена початковими даними!");
    }
}