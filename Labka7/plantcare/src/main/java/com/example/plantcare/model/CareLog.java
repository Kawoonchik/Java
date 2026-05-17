package com.example.plantcare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "care_logs")
public class CareLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plant_id")
    private Long plantId;

    @Column(name = "action_type")
    private String actionType;

    private String date;

    public CareLog() {}

    public CareLog(Long plantId, String actionType, String date) {
        this.plantId = plantId;
        this.actionType = actionType;
        this.date = date;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPlantId() { return plantId; }
    public void setPlantId(Long plantId) { this.plantId = plantId; }

    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}