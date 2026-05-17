package org.example.repository;

import org.example.model.Contract;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ContractRepository extends Neo4jRepository<Contract, Long> {

    // Запит 2: Знайти деталі по контракту (повертаємо кастомні поля через Map)
    @Query("MATCH (c:Contract {contractId: $contractId})-[:INCLUDES]->(t:Tour)-[:ACCOMMODATES_AT]->(h:Hotel) " +
            "RETURN c.contractId AS contract, t.name AS tour, h.name AS hotel, h.stars AS stars")
    List<Map<String, Object>> findContractDetails(String contractId);
}