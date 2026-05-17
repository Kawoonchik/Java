package org.example.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Contract")
public class Contract {
    @Id @GeneratedValue
    private Long id;

    private String contractId;
    private String date;
    private Double price;
    private Double commission;

    @Relationship(type = "INCLUDES", direction = Relationship.Direction.OUTGOING)
    private Tour tour;

    public Contract() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContractId() { return contractId; }
    public void setContractId(String contractId) { this.contractId = contractId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getCommission() { return commission; }
    public void setCommission(Double commission) { this.commission = commission; }

    public Tour getTour() { return tour; }
    public void setTour(Tour tour) { this.tour = tour; }
}