package com.example.weather_app.service;

import com.example.weather_app.dto.CityCordsDTO;
import com.example.weather_app.dto.OpenWeatherAPIKeyDTO;
import com.example.weather_app.dto.WeatherDTO;
import com.example.weather_app.model.OpenWeatherCordsWeatherData;
import com.example.weather_app.model.OpenWeatherLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Objects;

@Slf4j
@Service
public class OpenWeatherService {

    private final RestTemplate restTemplate;
    @Value("${openweather.api.key}")
    private String openWeatherAPIKey;
    @Value("${openweather.api.url}")
    private String openWeatherAPIURL;
    private final String openWeatherGeoAPIURL;
    private final String openWeatherAPIWeatherAPIURL;
    private static final String MEASURE_UNITS = "metric";

    public OpenWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.openWeatherGeoAPIURL = openWeatherAPIURL + "/geo/1.0/direct";
        this.openWeatherAPIWeatherAPIURL = openWeatherAPIURL + "/data/2.5/weather";
    }

    public OpenWeatherAPIKeyDTO getApiKey() {
        OpenWeatherAPIKeyDTO dto = new OpenWeatherAPIKeyDTO();
        dto.setApiKey("str");
        dto.setApiKey(openWeatherAPIKey);
        return dto;
    }

    public CityCordsDTO getCityCordsByName(String cityName) {
        String url = UriComponentsBuilder.fromHttpUrl(openWeatherGeoAPIURL)
                .queryParam("q", cityName)
                .queryParam("appid", openWeatherAPIKey)
                .toUriString();

        ResponseEntity<OpenWeatherLocation[]> response = restTemplate.getForEntity(url, OpenWeatherLocation[].class);
        OpenWeatherLocation[] cities = response.getBody();

        if (cities == null || cities.length == 0) {
            throw new RuntimeException("No city found with name: " + cityName);
        }

        OpenWeatherLocation firstCity = cities[0];
        CityCordsDTO cordsDTO = new CityCordsDTO();
        cordsDTO.setLon(firstCity.getLon());
        cordsDTO.setLat(firstCity.getLat());
        return cordsDTO;
    }

    public WeatherDTO getWeatherByCords(double lon, double lat) {
        String url = UriComponentsBuilder.fromHttpUrl(openWeatherAPIWeatherAPIURL)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", MEASURE_UNITS)
                .queryParam("appid", openWeatherAPIKey)
                .toUriString();
        log.info("url is {}", url);

        ResponseEntity<OpenWeatherCordsWeatherData> response = restTemplate.getForEntity(url, OpenWeatherCordsWeatherData.class);
        OpenWeatherCordsWeatherData weatherData = Objects.requireNonNull(response.getBody());

        WeatherDTO weatherDTO = new WeatherDTO();
        CityCordsDTO cordsDTO = new CityCordsDTO();
        cordsDTO.setLon(lon);
        cordsDTO.setLat(lat);

        weatherDTO.setCords(cordsDTO);
        weatherDTO.setTemperature(weatherData.getMain().getTemp());
        weatherDTO.setFeelsLikeTemperature(weatherData.getMain().getFeelsLike());
        weatherDTO.setAtmosphericPressure(weatherData.getMain().getPressure());
        weatherDTO.setWindSpeed(weatherData.getWind().getSpeed());
        weatherDTO.setCountryCode(weatherData.getSys().getCountry());
        weatherDTO.setCityName(weatherData.getName());

        return weatherDTO;
    }

    public WeatherDTO getWeatherByCityName(String cityName) {
        CityCordsDTO cityCords = getCityCordsByName(cityName);
        return getWeatherByCords(cityCords.getLon(), cityCords.getLat());
    }
}
