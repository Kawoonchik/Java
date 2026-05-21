package com.example.plantcare.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                //скритопульнінький сервер
                .servers(List.of(new Server().url("/").description("Прихований сервер")))
                .info(new Info()
                        .title("PlantCare API")
                        .description("REST API для застосунку по догляду за рослинами")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Kawoon")
                                .email("kawoon@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}