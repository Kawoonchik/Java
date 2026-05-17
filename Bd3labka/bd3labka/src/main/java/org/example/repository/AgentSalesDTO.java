package org.example.repository;

public class AgentSalesDTO {
    private String agentName;
    private Double totalSales;

    // Обов'язковий порожній конструктор
    public AgentSalesDTO() {}

    // Стандартні гетери та сетери
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }
}