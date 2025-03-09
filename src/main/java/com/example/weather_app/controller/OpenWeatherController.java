package com.example.weather_app.controller;

import com.example.weather_app.dto.CityCordsDTO;
import com.example.weather_app.dto.OpenWeatherAPIKeyDTO;
import com.example.weather_app.dto.WeatherDTO;
import com.example.weather_app.service.OpenWeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-weather")
@RequiredArgsConstructor
@Tag(name = "open-weather")
public class OpenWeatherController {

    private final OpenWeatherService openWeatherService;

    @GetMapping("/api-key")
    @Operation(summary = "Get OpenWeather API Key")
    public OpenWeatherAPIKeyDTO getApiKey() {
        return openWeatherService.getApiKey();
    }

    @GetMapping("/city-cords")
    @Operation(summary = "Get city coordinates by name")
    public CityCordsDTO getCityCords(@RequestParam("cityName") String cityName) {
        return openWeatherService.getCityCordsByName(cityName);
    }

    @GetMapping("/city-weather")
    @Operation(summary = "Get weather by city name")
    public WeatherDTO getCityWeather(@RequestParam("cityName") String cityName) {
        return openWeatherService.getWeatherByCityName(cityName);
    }

    @GetMapping("/weather-by-coords")
    @Operation(summary = "Get weather by coordinates")
    public WeatherDTO getCoordsWeather(@RequestParam("lon") double lon, @RequestParam("lat") double lat) {
        return openWeatherService.getWeatherByCords(lon, lat);
    }
}
