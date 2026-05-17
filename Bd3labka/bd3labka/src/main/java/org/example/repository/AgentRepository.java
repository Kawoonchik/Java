package org.example.repository;

import org.example.model.Agent;
import org.example.model.Contract;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AgentRepository extends Neo4jRepository<Agent, Long> {

    // 1. Отримати всі контракти конкретного агента (повертає сутності Contract)
    @Query("MATCH (a:Agent {name: $agentName})-[:MANAGES]->(c:Contract) RETURN c")
    List<Contract> findContractsByAgentName(String agentName);

    // 3. Знайти всіх агентів, які продавали тури в готелі з певною кількістю зірок
    @Query("MATCH (a:Agent)-[:MANAGES]->(:Contract)-[:INCLUDES]->(:Tour)-[:ACCOMMODATES_AT]->(h:Hotel {stars: $stars}) RETURN DISTINCT a")
    List<Agent> findAgentsByHotelStars(Integer stars);

    // 4. Підрахувати загальну суму продажів (контрактів) для кожного агента
    // Оскільки повертається не сутність, а кастомні дані, використовуємо List<Map<String, Object>> або створюємо DTO
// 4. Підрахувати загальну суму продажів (контрактів) для кожного агента
    @Query("MATCH (a:Agent)-[:MANAGES]->(c:Contract) RETURN a.name AS agentName, SUM(c.totalAmount) AS totalSales")
    List<AgentSalesDTO> calculateTotalSalesPerAgent();
}