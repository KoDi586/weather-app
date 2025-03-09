package com.example.weather_app.model;

import lombok.Data;

import java.util.Map;

@Data
public class OpenWeatherLocation {
    private String name;
    private Map<String, String> localNames;
    private double lat;
    private double lon;
    private String country;
}
