package com.example.weather_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OpenWeatherAPIKeyDTO {

    @Schema(example = "your-api-key")
    private String apiKey;
}
