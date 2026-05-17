package org.example.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Hotel")
public class Hotel {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private Integer stars;
    private String city;

    // Обов'язковий порожній конструктор
    public Hotel() {}

    // Гетери та сетери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getStars() { return stars; }
    public void setStars(Integer stars) { this.stars = stars; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}