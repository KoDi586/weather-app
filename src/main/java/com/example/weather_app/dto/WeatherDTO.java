package com.example.weather_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WeatherDTO {

    @Schema(example = "16.12")
    private double temperature;

    @Schema(example = "16")
    private double feelsLikeTemperature;

    @Schema(example = "1012")
    private int atmosphericPressure;

    @Schema(example = "4.37")
    private double windSpeed;

    @Schema(example = "RU")
    private String countryCode;

    @Schema(example = "Pyatigorsk")
    private String cityName;

    @Schema(implementation = CityCordsDTO.class)
    private CityCordsDTO cords;
}
