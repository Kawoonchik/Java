package com.example.plantcare.repository;

import com.example.plantcare.model.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {


    Page<Plant> findAll(Pageable pageable);


    @Query(value = "SELECT p FROM Plant p " +
            "LEFT JOIN FETCH p.user " +
            "LEFT JOIN FETCH p.sensorData " +
            "LEFT JOIN FETCH p.diseases " +
            "LEFT JOIN FETCH p.careLogs",
            countQuery = "SELECT count(p) FROM Plant p")
    Page<Plant> findAllWithFetch(Pageable pageable);
}