package com.example.plantcare;

import com.example.plantcare.model.*;
import com.example.plantcare.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

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
        Disease d1 = diseaseRepository.save(new Disease("Коренева гниль", "Зменшити полив"));
        Disease d2 = diseaseRepository.save(new Disease("Попелиця", "Обробити мильним розчином"));
        Disease d3 = diseaseRepository.save(new Disease("Борошниста роса", "Видалити уражене листя"));

        //5 користувачів
        for (int i = 1; i <= 5; i++) {
            User user = new User("User_" + i, "user" + i + "@plantcare.com");
            userRepository.save(user);

            //користувач має по 4 рослини
            for (int j = 1; j <= 4; j++) {
                Plant plant = new Plant("Рослина " + i + "-" + j, "Species_" + j);
                plant.setUser(user);

                Set<Disease> plantDiseases = new HashSet<>();
                if (j % 2 == 0) plantDiseases.add(d1);
                if (j % 3 == 0) plantDiseases.add(d2);
                plant.setDiseases(plantDiseases);

                plantRepository.save(plant);

                SensorData sensor = new SensorData(plant, 40 + j * 5, 70 + j * 2);
                sensorDataRepository.save(sensor);

                //кожній рослині по 3 записи
                for (int k = 1; k <= 3; k++) {
                    CareLog careLog = new CareLog(plant, "Полив " + k, "2026-05-0" + k);
                    careLogRepository.save(careLog);
                }
            }
        }
        System.out.println(">>> База даних наповнена: 5 користувачів, 20 рослин, 60 логів догляду!");
    }
}