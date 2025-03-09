package com.example.weather_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CityCordsDTO {

    @Schema(example = "51")
    private double lat;

    @Schema(example = "51")
    private double lon;
}