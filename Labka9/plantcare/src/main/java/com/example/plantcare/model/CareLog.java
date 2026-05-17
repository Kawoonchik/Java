package com.example.plantcare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "care_logs")
public class CareLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id")
    private Plant plant;

    private String actionType;
    private String date;

    public CareLog() {}
    public CareLog(Plant plant, String actionType, String date) {
        this.plant = plant;
        this.actionType = actionType;
        this.date = date;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Plant getPlant() { return plant; }
    public void setPlant(Plant plant) { this.plant = plant; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}