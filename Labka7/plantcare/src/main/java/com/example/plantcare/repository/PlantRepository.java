//package com.example.plantcare.repository;
//
//import com.example.plantcare.model.Plant;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;

//@Repository
//public class PlantRepository {
//    private final Map<String, Plant> storage = new ConcurrentHashMap<>();
//
//    public List<Plant> findAll() {
//        return new ArrayList<>(storage.values());
//    }
//
//    public Plant findById(String id) {
//        return storage.get(id);
//    }
//
//    public Plant save(Plant plant) {
//        storage.put(plant.getId(), plant);
//        return plant;
//    }
//
//    public void deleteById(String id) {
//        storage.remove(id);
//    }
//}

package com.example.plantcare.repository;

import com.example.plantcare.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

}