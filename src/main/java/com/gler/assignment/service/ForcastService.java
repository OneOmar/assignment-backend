package com.gler.assignment.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gler.assignment.dto.ForcastRequest;
import com.gler.assignment.entity.Forcast;
import com.gler.assignment.repository.ForcastRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class ForcastService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ForcastRepository forcastRepository;

    public ForcastService(ForcastRepository forcastRepository) {
        this.forcastRepository = forcastRepository;
    }

    public Forcast getForecast(ForcastRequest request) {

        String url = "https://api.open-meteo.com/v1/forecast"
                + "?latitude=52.52&longitude=13.41"
                + "&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m";

        try {
            // Initialize ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // Call external API
            String response = restTemplate.getForObject(url, String.class);

            // Parse JSON response
            JsonNode root = mapper.readTree(response);
            JsonNode hourly = root.get("hourly");
            JsonNode temperatures = hourly.get("temperature_2m");
            JsonNode humidity = hourly.get("relative_humidity_2m");
            JsonNode windSpeed = hourly.get("wind_speed_10m");

            double maxTemp = getMax(temperatures);
            double maxHumidity = getMax(humidity);
            double maxWind = getMax(windSpeed);

            Forcast forecast = new Forcast();
            forecast.setDate(LocalDate.now());

            if (request.getAddTemperature()) {
                forecast.setTemperature(maxTemp);
            }
            if (request.getAddHumidity()) {
                forecast.setHumidity(maxHumidity);
            }
            if (request.getAddWindSpeed()) {
                forecast.setWindSpeed(maxWind);
            }

            return forcastRepository.save(forecast);

        } catch (Exception e) {
            throw new RuntimeException("Upstream API Unreachable");
        }
    }

    private double getMax(JsonNode array) {
        double max = Double.MIN_VALUE;

        for (JsonNode node : array) {
            double value = node.asDouble();
            if (value > max) {
                max = value;
            }
        }

        return max;
    }
}