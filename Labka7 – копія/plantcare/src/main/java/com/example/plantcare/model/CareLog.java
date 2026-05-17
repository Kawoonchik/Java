package com.example.plantcare.model;

public class CareLog {
    private String id;
    private String plantId;
    private String actionType;
    private String date;

    public CareLog() {}

    public CareLog(String id, String plantId, String actionType, String date) {
        this.id = id;
        this.plantId = plantId;
        this.actionType = actionType;
        this.date = date;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPlantId() { return plantId; }
    public void setPlantId(String plantId) { this.plantId = plantId; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}