package org.example;

import org.example.repository.AgentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab11GraphDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab11GraphDbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AgentRepository agentRepository) {
        return args -> {
            System.out.println("=== Тестування графової БД Neo4j ===");

            // Тест Запиту 3: Агенти, що продають 5-зіркові готелі
            System.out.println("\nАгенти (5-зіркові готелі):");
            agentRepository.findAgentsByHotelStars(5).forEach(agent ->
                    System.out.println("- " + agent.getName())
            );


            System.out.println("\nСума продажів по агентам:");
            agentRepository.calculateTotalSalesPerAgent().forEach(dto ->
                    System.out.println(dto.getAgentName() + " : " + dto.getTotalSales() + "$")
            );

            System.out.println("\n====================================");
        };
    }
}