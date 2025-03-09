package com.example.weather_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MessageDTO {

    @Schema(example = "up")
    @NotBlank
    private String message;
}
