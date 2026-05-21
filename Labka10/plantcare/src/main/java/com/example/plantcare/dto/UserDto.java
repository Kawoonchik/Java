package com.example.plantcare.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO для сутності Користувача")
public class UserDto {

    @Schema(description = "Унікальний ідентифікатор користувача", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Ім'я користувача (нікнейм)", example = "plant_lover99")
    private String username;

    @NotBlank
    @Email
    @Schema(description = "Електронна пошта користувача", example = "user@example.com")
    private String email;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}