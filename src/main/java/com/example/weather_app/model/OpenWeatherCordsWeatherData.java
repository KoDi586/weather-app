package com.example.weather_app.model;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherCordsWeatherData {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    @Data
    public static class Coord {
        private double lon;
        private double lat;
    }

    @Data
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Main {
        private double temp;
        private double feelsLike;
        private double tempMin;
        private double tempMax;
        private int pressure;
        private int humidity;
        private Integer seaLevel;
        private Integer grndLevel;
    }

    @Data
    public static class Wind {
        private double speed;
        private int deg;
        private Double gust;
    }

    @Data
    public static class Clouds {
        private int all;
    }

    @Data
    public static class Sys {
        private int type;
        private int id;
        private String country;
        private long sunrise;
        private long sunset;
    }
}
